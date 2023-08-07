package com.pallasathenagroup.querydsl;

import com.pallasathenagroup.querydsl.array.ArrayOps;
import com.pallasathenagroup.querydsl.hstore.HstoreOps;
import com.pallasathenagroup.querydsl.json.JsonOps;
import com.pallasathenagroup.querydsl.yearmonth.YearMonthOps;
import com.querydsl.jpa.HQLTemplates;

public class ExtendedHQLTemplates extends HQLTemplates {

    public static final ExtendedHQLTemplates DEFAULT = new ExtendedHQLTemplates();

    public ExtendedHQLTemplates() {
        registerTemplates(this);
    }

    public static void registerTemplates(ExtendedHQLTemplates templates) {
        templates.add(ArrayOps.OVERLAPS, "ARRAY_OVERLAPS({0}, {1}) = TRUE");
        templates.add(ArrayOps.CONTAINS, "ARRAY_CONTAINS({0}, {1}) = TRUE");
        templates.add(ArrayOps.CONTAINS_ELEMENT, "ARRAY_CONTAINS_ELEMENT({0}, {1}) = TRUE");
        templates.add(ArrayOps.IS_CONTAINED_BY, "ARRAY_IS_CONTAINED_BY({0}, {1}) = TRUE");
        templates.add(ArrayOps.CONCAT, "ARRAY_CONCAT({0}, {1})");
        templates.add(ArrayOps.APPEND, "ARRAY_APPEND({0}, {1})");
        templates.add(ArrayOps.PREPEND, "ARRAY_PREPEND({0}, {1})");
        templates.add(ArrayOps.NDIMS, "ARRAY_NDIMS({0})");
        templates.add(ArrayOps.DIMS, "ARRAY_DIMS({0})");
        templates.add(ArrayOps.FILL, "ARRAY_FILL({0}, {1})");
        templates.add(ArrayOps.LENGTH, "ARRAY_LENGTH({0})");
        templates.add(ArrayOps.TOSTRING, "ARRAY_TOSTRING({0})");
        templates.add(ArrayOps.UNNEST, "ARRAY_UNNEST({0})");
        templates.add(ArrayOps.ELEMENT_AT, "ARRAY_ELEMENT_AT({0}, {1})");
        templates.add(ArrayOps.ARRAY_AGG, "ARRAY_AGG({0})");

        templates.add(JsonOps.CONTAINS_KEY, "JSON_CONTAINS_KEY({0}, {1}) = TRUE");
        templates.add(JsonOps.GET, "JSON_GET({0}, {1})");
        templates.add(JsonOps.GET_TEXT, "JSON_GET_TEXT({0}, {1})");
        templates.add(JsonOps.CONCAT, "JSON_CONCAT({0}, {1})");
        templates.add(JsonOps.MAP_SIZE, "jsonb_array_length({0}, {1})");
        templates.add(JsonOps.KEYS, "jsonb_object_keys({0})");
        templates.add(JsonOps.ELEMENTS, "jsonb_array_elements_text({0})");
        templates.add(JsonOps.JSON_BUILD_OBJECT, "jsonb_build_object({0})");
        templates.add(JsonOps.JSON_BUILD_ARRAY, "jsonb_build_array({0})");

        templates.add(HstoreOps.CONTAINS_KEY, "HSTORE_CONTAINS_KEY({0}, {1}) = TRUE");
        templates.add(HstoreOps.MAP_SIZE, "HSTORE_MAP_SIZE({0})");
        templates.add(HstoreOps.GET, "HSTORE_GET({0},{1})");
        templates.add(HstoreOps.MAP_IS_EMPTY, "HSTORE_MAP_IS_EMPTY({0})");
        templates.add(HstoreOps.CONCAT, "HSTORE_CONCAT({0},{1})");
        templates.add(HstoreOps.KEYS, "skeys({0})");
        templates.add(HstoreOps.VALUES, "svals({0})");

        templates.add(YearMonthOps.CAST_YEARMONTH, "CAST_YEARMONTH({0})");
        templates.add(YearMonthOps.CAST_MONTH, "CAST_MONTH({0})");
        templates.add(YearMonthOps.CAST_YEAR, "CAST_YEAR({0})");
    }

}
