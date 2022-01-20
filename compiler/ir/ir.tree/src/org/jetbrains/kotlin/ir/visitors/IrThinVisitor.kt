/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.visitors

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*

abstract class IrThinVisitor<out R, in D> {
    abstract fun visitElement(element: IrElement, data: D): R

    open fun visitModuleFragment(declaration: IrModuleFragment, data: D): R =
        visitElement(declaration, data)

    open fun visitFile(declaration: IrFile, data: D): R =
        visitElement(declaration, data)

    open fun visitExternalPackageFragment(declaration: IrExternalPackageFragment, data: D): R =
        visitElement(declaration, data)

    open fun visitDeclaration(declaration: IrDeclaration, data: D): R =
        visitElement(declaration, data)

    open fun visitScript(declaration: IrScript, data: D): R =
        visitDeclaration(declaration, data)

    open fun visitClass(declaration: IrClass, data: D): R =
        visitDeclaration(declaration, data)

    open fun visitSimpleFunction(declaration: IrSimpleFunction, data: D): R =
        visitDeclaration(declaration, data)

    open fun visitConstructor(declaration: IrConstructor, data: D): R =
        visitDeclaration(declaration, data)

    open fun visitProperty(declaration: IrProperty, data: D) =
        visitDeclaration(declaration, data)

    open fun visitField(declaration: IrField, data: D) =
        visitDeclaration(declaration, data)

    open fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty, data: D) =
        visitDeclaration(declaration, data)

    open fun visitVariable(declaration: IrVariable, data: D) =
        visitDeclaration(declaration, data)

    open fun visitEnumEntry(declaration: IrEnumEntry, data: D) =
        visitDeclaration(declaration, data)

    open fun visitAnonymousInitializer(declaration: IrAnonymousInitializer, data: D) =
        visitDeclaration(declaration, data)

    open fun visitTypeParameter(declaration: IrTypeParameter, data: D) =
        visitDeclaration(declaration, data)

    open fun visitValueParameter(declaration: IrValueParameter, data: D) =
        visitDeclaration(declaration, data)

    open fun visitTypeAlias(declaration: IrTypeAlias, data: D) =
        visitDeclaration(declaration, data)

    open fun visitBody(body: IrBody, data: D): R =
        visitElement(body, data)

    open fun visitExpressionBody(body: IrExpressionBody, data: D) =
        visitBody(body, data)

    open fun visitBlockBody(body: IrBlockBody, data: D) =
        visitBody(body, data)

    open fun visitSyntheticBody(body: IrSyntheticBody, data: D) =
        visitBody(body, data)

    open fun visitExpression(expression: IrExpression, data: D) =
        visitElement(expression, data)

    open fun visitSuspendableExpression(expression: IrSuspendableExpression, data: D) =
        visitExpression(expression, data)

    open fun visitSuspensionPoint(expression: IrSuspensionPoint, data: D) =
        visitExpression(expression, data)

    open fun visitConst(expression: IrConst<*>, data: D) =
        visitExpression(expression, data)

    open fun visitConstantObject(expression: IrConstantObject, data: D) =
        visitExpression(expression, data)

    open fun visitConstantPrimitive(expression: IrConstantPrimitive, data: D) =
        visitExpression(expression, data)

    open fun visitConstantArray(expression: IrConstantArray, data: D) =
        visitExpression(expression, data)

    open fun visitVararg(expression: IrVararg, data: D) =
        visitExpression(expression, data)

    open fun visitSpreadElement(spread: IrSpreadElement, data: D) =
        visitElement(spread, data)

    open fun visitBlock(expression: IrBlock, data: D) =
        visitExpression(expression, data)

    open fun visitComposite(expression: IrComposite, data: D) =
        visitExpression(expression, data)

    open fun visitStringConcatenation(expression: IrStringConcatenation, data: D) =
        visitExpression(expression, data)

    open fun visitGetObjectValue(expression: IrGetObjectValue, data: D) =
        visitExpression(expression, data)

    open fun visitGetEnumValue(expression: IrGetEnumValue, data: D) =
        visitExpression(expression, data)

    open fun visitGetValue(expression: IrGetValue, data: D) =
        visitExpression(expression, data)

    open fun visitSetValue(expression: IrSetValue, data: D) =
        visitExpression(expression, data)

    open fun visitGetField(expression: IrGetField, data: D) =
        visitExpression(expression, data)

    open fun visitSetField(expression: IrSetField, data: D) =
        visitExpression(expression, data)

    open fun visitCall(expression: IrCall, data: D) =
        visitExpression(expression, data)

    open fun visitConstructorCall(expression: IrConstructorCall, data: D) =
        visitExpression(expression, data)

    open fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall, data: D) =
        visitExpression(expression, data)

    open fun visitEnumConstructorCall(expression: IrEnumConstructorCall, data: D) =
        visitExpression(expression, data)

    open fun visitGetClass(expression: IrGetClass, data: D) =
        visitExpression(expression, data)

    open fun visitFunctionReference(expression: IrFunctionReference, data: D) =
        visitExpression(expression, data)

    open fun visitPropertyReference(expression: IrPropertyReference, data: D) =
        visitExpression(expression, data)

    open fun visitLocalDelegatedPropertyReference(expression: IrLocalDelegatedPropertyReference, data: D) =
        visitExpression(expression, data)

    open fun visitRawFunctionReference(expression: IrRawFunctionReference, data: D) =
        visitExpression(expression, data)

    open fun visitFunctionExpression(expression: IrFunctionExpression, data: D) =
        visitExpression(expression, data)

    open fun visitClassReference(expression: IrClassReference, data: D) =
        visitExpression(expression, data)

    open fun visitInstanceInitializerCall(expression: IrInstanceInitializerCall, data: D) =
        visitExpression(expression, data)

    open fun visitTypeOperator(expression: IrTypeOperatorCall, data: D) =
        visitExpression(expression, data)

    open fun visitWhen(expression: IrWhen, data: D) =
        visitExpression(expression, data)

    open fun visitBranch(branch: IrBranch, data: D) =
        visitElement(branch, data)

    open fun visitElseBranch(branch: IrElseBranch, data: D) =
        visitBranch(branch, data)

    open fun visitWhileLoop(loop: IrWhileLoop, data: D) =
        visitExpression(loop, data)

    open fun visitDoWhileLoop(loop: IrDoWhileLoop, data: D) =
        visitExpression(loop, data)

    open fun visitTry(aTry: IrTry, data: D) =
        visitExpression(aTry, data)

    open fun visitCatch(aCatch: IrCatch, data: D) =
        visitElement(aCatch, data)

    open fun visitBreak(jump: IrBreak, data: D) =
        visitExpression(jump, data)

    open fun visitContinue(jump: IrContinue, data: D) =
        visitExpression(jump, data)

    open fun visitReturn(expression: IrReturn, data: D) =
        visitExpression(expression, data)

    open fun visitThrow(expression: IrThrow, data: D) =
        visitExpression(expression, data)

    open fun visitDynamicOperatorExpression(expression: IrDynamicOperatorExpression, data: D) =
        visitExpression(expression, data)

    open fun visitDynamicMemberExpression(expression: IrDynamicMemberExpression, data: D) =
        visitExpression(expression, data)

    open fun visitErrorDeclaration(declaration: IrErrorDeclaration, data: D) =
        visitDeclaration(declaration, data)

    open fun visitErrorExpression(expression: IrErrorExpression, data: D) =
        visitExpression(expression, data)

    open fun visitErrorCallExpression(expression: IrErrorCallExpression, data: D) =
        visitErrorExpression(expression, data)
}
