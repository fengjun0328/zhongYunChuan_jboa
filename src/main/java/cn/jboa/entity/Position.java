package cn.jboa.entity;

import java.util.Objects;

public class Position {
    private int id;
    private String nameCn;
    private String nameEn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id &&
                Objects.equals(nameCn, position.nameCn) &&
                Objects.equals(nameEn, position.nameEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCn, nameEn);
    }
}
