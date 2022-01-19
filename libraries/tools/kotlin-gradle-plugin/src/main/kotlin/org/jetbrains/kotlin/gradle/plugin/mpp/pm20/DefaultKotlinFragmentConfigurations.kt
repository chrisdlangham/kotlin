/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.plugin.mpp.pm20

import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category


val DefaultKotlinCompileDependenciesDefinition = KotlinGradleFragmentConfigurationDefinition(
    provider = {
        project.configurations.maybeCreate(disambiguateName("compileDependencies")).apply {
            isCanBeConsumed = false
            isCanBeResolved = true
        }
    },
    relations = { configuration ->
        configuration.extendsFrom(dependencies.transitiveApiConfiguration)
        configuration.extendsFrom(dependencies.transitiveImplementationConfiguration)
    },
    attributes = KotlinFragmentPlatformAttributes + KotlinFragmentConsumerApiUsageAttribute
)

val DefaultKotlinRuntimeDependenciesDefinition = ConfigurationDefinition(
    provider = {
        project.configurations.maybeCreate(disambiguateName("runtimeDependencies")).apply {
            isCanBeConsumed = false
            isCanBeResolved = true
        }
    },
    attributes = KotlinFragmentPlatformAttributes + KotlinFragmentConsumerRuntimeUsageAttribute,
    relations = { configuration ->
        configuration.extendsFrom(dependencies.transitiveApiConfiguration)
        configuration.extendsFrom(dependencies.transitiveImplementationConfiguration)
    }
)

val DefaultKotlinApiElementsDefinition = ConfigurationDefinition(
    provider = {
        module.project.configurations.maybeCreate(disambiguateName("apiElements")).apply {
            isCanBeResolved = false
            isCanBeConsumed = false
            module.ifMadePublic { isCanBeConsumed = true }
        }
    },
    relations = { configuration -> configuration.extendsFrom(dependencies.transitiveApiConfiguration) },
    capabilities = KotlinFragmentModuleCapability,
    attributes = KotlinFragmentPlatformAttributes + KotlinFragmentProducerApiUsageAttribute + { fragment ->
        attribute(Category.CATEGORY_ATTRIBUTE, fragment.project.objects.named(Category::class.java, Category.LIBRARY))
        attribute(Bundling.BUNDLING_ATTRIBUTE, fragment.project.objects.named(Bundling::class.java, Bundling.EXTERNAL))
    },
)

val DefaultKotlinRuntimeElementsDefinition = ConfigurationDefinition(
    provider = {
        module.project.configurations.maybeCreate(disambiguateName("runtimeElements")).apply {
            isCanBeResolved = false
            isCanBeConsumed = false
            module.ifMadePublic { isCanBeConsumed = true }
        }
    },
    relations = { configuration ->
        configuration.extendsFrom(dependencies.transitiveApiConfiguration)
        configuration.extendsFrom(dependencies.transitiveImplementationConfiguration)
    },
    attributes = KotlinFragmentPlatformAttributes + KotlinFragmentProducerRuntimeUsageAttribute + { fragment ->
        attributes.attribute(Category.CATEGORY_ATTRIBUTE, fragment.project.objects.named(Category::class.java, Category.LIBRARY))
        attributes.attribute(Bundling.BUNDLING_ATTRIBUTE, fragment.project.objects.named(Bundling::class.java, Bundling.EXTERNAL))
    },
    capabilities = KotlinFragmentModuleCapability.onlyIfMadePublic()
)

val DefaultKotlinHostSpecificMetadataElementsDefinition = ConfigurationDefinition(
    provider = {
        project.configurations.maybeCreate(disambiguateName("hostSpecificMetadataElements")).apply {
            isCanBeResolved = false
            isCanBeConsumed = false
        }
    },
    attributes = KotlinFragmentPlatformAttributes + KotlinFragmentKonanTargetAttribute + KotlinFragmentMetadataUsageAttribute,
    artifacts = KotlinFragmentHostSpecificMetadataArtifact
)

