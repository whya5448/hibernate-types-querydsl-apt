package com.pallasathenagroup.querydsl;

import com.pallasathenagroup.querydsl.array.ArrayOps;
import com.pallasathenagroup.querydsl.array.PostgresqlArrayExpression;
import com.pallasathenagroup.querydsl.array.PostgresqlArrayOperation;
import com.pallasathenagroup.querydsl.hstore.HstoreExpression;
import com.pallasathenagroup.querydsl.yearmonth.YearMonthExpression;
import com.pallasathenagroup.querydsl.yearmonth.YearMonthOps;
import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.*;
import io.hypersistence.utils.hibernate.type.basic.Iso8601MonthType;
import io.hypersistence.utils.hibernate.type.basic.YearMonthTimestampType;
import io.hypersistence.utils.hibernate.type.basic.YearType;
import org.hibernate.query.TypedParameterValue;

import java.lang.reflect.Array;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.util.Map;

import static com.pallasathenagroup.querydsl.yearmonth.YearMonthOps.CAST_YEARMONTH;

public final class HibernateTypesExpressions {

    @SafeVarargs
    public static <T> PostgresqlArrayExpression<T[], T> createArrayExpression(T... elements) {
        return new PostgresqlArrayExpression<T[], T>((Expression) Expressions.constant(PostgresqlArrayExpression.getTypedParameterValue(elements, null)), null);
    }

    @SafeVarargs
    public static <T extends Enum<? extends T>> PostgresqlArrayExpression<T[], T> createArrayExpression(String enumType, T... elements) {
        return new PostgresqlArrayExpression<T[], T>((Expression) Expressions.constant(PostgresqlArrayExpression.getTypedParameterValue(elements, enumType)), enumType);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static YearMonthExpression yearMonth(YearMonth yearMonth) {
        return new YearMonthExpression((Constant) Expressions.constant(new TypedParameterValue(YearMonthTimestampType.INSTANCE, yearMonth)));
    }

    public static YearMonthExpression yearMonth(DateExpression<?> dateExpression) {
        return new YearMonthExpression(Expressions.operation(YearMonth.class, CAST_YEARMONTH, Expressions.operation(dateExpression.getType(), Ops.DateTimeOps.TRUNC_MONTH, dateExpression)));
    }

    public static YearMonthExpression yearMonth(DateTimeExpression<?> dateExpression) {
        return new YearMonthExpression(Expressions.operation(YearMonth.class, CAST_YEARMONTH, Expressions.operation(dateExpression.getType(), Ops.DateTimeOps.TRUNC_MONTH, dateExpression)));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ComparableExpression<Month> month(Month month) {
        return Expressions.asComparable((Constant) Expressions.constant(new TypedParameterValue(Iso8601MonthType.INSTANCE, month)));
    }

    public static ComparableExpression<Month> month(NumberExpression<Integer> expression) {
        return Expressions.enumOperation(Month.class, YearMonthOps.CAST_MONTH, expression);
    }

    public static ComparableExpression<Month> month(DateExpression<?> expression) {
        return month(expression.month());
    }

    public static ComparableExpression<Month> month(DateTimeExpression<?> expression) {
        return month(expression.month());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ComparableExpression<Year> year(Year year) {
        return Expressions.asComparable((Constant) Expressions.constant(new TypedParameterValue(YearType.INSTANCE, year)));
    }

    public static ComparableExpression<Year> year(NumberExpression<Integer> expression) {
        return Expressions.comparableOperation(Year.class, YearMonthOps.CAST_YEAR, expression);
    }

    public static ComparableExpression<Year> year(DateExpression<?> expression) {
        return year(expression.year());
    }

    public static ComparableExpression<Year> year(DateTimeExpression<?> expression) {
        return year(expression.year());
    }

    public static <T extends Comparable<? super T>> PostgresqlArrayOperation<T[], T> arrayAgg(Expression<T> expression) {
        return new PostgresqlArrayOperation(Expressions.operation(Array.newInstance(expression.getType(), 0).getClass(), ArrayOps.ARRAY_AGG, expression), null);
    }

    public static HstoreExpression asHstore(Expression<Map<String, String>> expression) {
        return new HstoreExpression(expression);
    }

}
