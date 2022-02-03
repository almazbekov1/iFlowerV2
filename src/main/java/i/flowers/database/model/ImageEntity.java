//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "image"
)
public class ImageEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String fileName;

    public ImageEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }




}
