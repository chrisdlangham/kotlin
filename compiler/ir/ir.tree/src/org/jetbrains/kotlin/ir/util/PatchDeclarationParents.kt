/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.util

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.visitors.IrThinVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import java.util.*

fun <T : IrElement> T.patchDeclarationParents(initialParent: IrDeclarationParent? = null) =
    apply {
        val visitor = initialParent?.let { PatchDeclarationParentsVisitor(it) } ?: PatchDeclarationParentsVisitor()
        acceptVoid(visitor)
    }

class PatchDeclarationParentsVisitor() : IrThinVisitorVoid() {

    constructor(containingDeclaration: IrDeclarationParent) : this() {
        declarationParentsStack.push(containingDeclaration)
    }

    private val declarationParentsStack = ArrayDeque<IrDeclarationParent>()

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    private fun visitPackageFragment(declaration: IrPackageFragment) {
        declarationParentsStack.push(declaration)
        visitElement(declaration)
        declarationParentsStack.pop()
    }

    override fun visitFile(declaration: IrFile) {
        visitPackageFragment(declaration)
    }

    override fun visitExternalPackageFragment(declaration: IrExternalPackageFragment) {
        visitPackageFragment(declaration)
    }

    private fun visitDeclaration(declaration: IrDeclarationBase) {
        declaration.parent = declarationParentsStack.peekFirst()

        if (declaration is IrDeclarationParent) {
            declarationParentsStack.push(declaration)
        }

        visitElement(declaration)

        if (declaration is IrDeclarationParent) {
            declarationParentsStack.pop()
        }
    }

    override fun visitScript(declaration: IrScript) {
        visitDeclaration(declaration)
    }

    override fun visitClass(declaration: IrClass) {
        visitDeclaration(declaration)
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction) {
        visitDeclaration(declaration)
    }

    override fun visitConstructor(declaration: IrConstructor) {
        visitDeclaration(declaration)
    }

    override fun visitProperty(declaration: IrProperty) {
        visitDeclaration(declaration)
    }

    override fun visitField(declaration: IrField) {
        visitDeclaration(declaration)
    }

    override fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty) {
        visitDeclaration(declaration)
    }

    override fun visitVariable(declaration: IrVariable) {
        visitDeclaration(declaration)
    }

    override fun visitEnumEntry(declaration: IrEnumEntry) {
        visitDeclaration(declaration)
    }

    override fun visitAnonymousInitializer(declaration: IrAnonymousInitializer) {
        visitDeclaration(declaration)
    }

    override fun visitTypeParameter(declaration: IrTypeParameter) {
        visitDeclaration(declaration)
    }

    override fun visitValueParameter(declaration: IrValueParameter) {
        visitDeclaration(declaration)
    }

    override fun visitTypeAlias(declaration: IrTypeAlias) {
        visitDeclaration(declaration)
    }
}
