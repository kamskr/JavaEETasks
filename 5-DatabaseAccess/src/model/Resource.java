package model;

import java.util.Objects;

public class Resource {
    private int idResource;
    private String name;
    private String content;

    public Resource() {

    }

    public Resource(int idResource, String name, String content) {
        this.idResource = idResource;
        this.name = name;
        this.content = content;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "idResource=" + idResource +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return idResource == resource.idResource &&
                Objects.equals(name, resource.name) &&
                Objects.equals(content, resource.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idResource, name, content);
    }
}
