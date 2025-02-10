package com.workintech.sqldmljoins.entity;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

@NamedNativeQuery(name = "findClassesWithBookCount",
        query = "SELECT sinif, COUNT(kitap_id) FROM ogrenci GROUP BY sinif",
        resultSetMapping = "Mapping.KitapCount")
@SqlResultSetMapping(name = "Mapping.KitapCount",
        classes = @ConstructorResult(targetClass = KitapCount.class,
                columns = {
                        @ColumnResult(name = "sinif"),
                        @ColumnResult(name = "count")
                }))
public interface KitapCount {

    // Changed return type of getCount() to Integer to handle null values
    String getSinif();

    // Return Integer to handle cases where count may be null
    Integer getCount();
}
