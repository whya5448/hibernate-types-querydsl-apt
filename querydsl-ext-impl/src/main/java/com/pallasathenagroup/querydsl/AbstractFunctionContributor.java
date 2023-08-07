package com.pallasathenagroup.querydsl;

import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.metamodel.mapping.BasicValuedMapping;
import org.hibernate.query.ReturnableType;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.query.sqm.produce.function.FunctionReturnTypeResolver;
import org.hibernate.query.sqm.tree.SqmTypedNode;
import org.hibernate.sql.ast.tree.SqlAstNode;
import org.hibernate.type.spi.TypeConfiguration;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractFunctionContributor implements FunctionContributor {
    static final FunctionReturnTypeResolver RESOLVER = new FunctionReturnTypeResolver() {
        @Override
        public ReturnableType<?> resolveFunctionReturnType(ReturnableType<?> impliedType, List<? extends SqmTypedNode<?>> arguments, TypeConfiguration typeConfiguration) {
            return impliedType;
        }

        @Override
        public BasicValuedMapping resolveFunctionReturnType(Supplier<BasicValuedMapping> impliedTypeAccess, List<? extends SqlAstNode> arguments) {
            return impliedTypeAccess.get();
        }
    };

    void registerPattern(SqmFunctionRegistry functionRegistry, String functionName, String pattern, Object returnType) {
        functionRegistry.patternDescriptorBuilder(functionName, pattern)
                .setReturnTypeResolver(RESOLVER)
                .register();
    }

    void register(SqmFunctionRegistry functionRegistry, String functionName, Object returnType) {
        functionRegistry.namedDescriptorBuilder(functionName)
                .setReturnTypeResolver(RESOLVER)
                .register();
    }

}
