package com.mycoloruniverse.actsbills.models;

import java.util.HashMap;
import java.util.Map;

/**
    В PropertyAttr есть кнопка. На ее можно повесить что-то. Например, справочник.
    Для этого нужно указать тип кнопки, и что там висит, а также какие атрибуты будут меняться
    в этом поле. И какие данные можно там хранить.
 */

public class PropertyButton {
    private String id;  //  Уникальность кноки. Одна кнопка может висеть на нескольких позициях адаптера
    private EPropertyButton directory;  // Справочник
    private int icon_id;    // Если нужно заменить иконку, если стандартная то передаем null
    private final Map<String, Object> result = new HashMap<>();  // Результат может быть возвращен любой

    public PropertyButton(String id, EPropertyButton directory, int icon_id) {
        this.id = id;
        this.directory = directory;
        this.icon_id = icon_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EPropertyButton getDirectory() {
        return directory;
    }

    public void setDirectory(EPropertyButton directory) {
        this.directory = directory;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    public Map<String, Object> getResult() {
        return result;
    }
}
