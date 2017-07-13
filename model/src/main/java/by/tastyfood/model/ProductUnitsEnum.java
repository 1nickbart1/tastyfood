package by.tastyfood.model;

/**
 * Created by Nikolay on 06.06.2017.
 */
public enum ProductUnitsEnum {
    QUANTITY(1L),
    WEIGHT(2L);

    private ProductUnitsEnum(Long id){
        this.id = id;
    }

    private Long id;

    public Long getId() {
        return id;
    }
}
