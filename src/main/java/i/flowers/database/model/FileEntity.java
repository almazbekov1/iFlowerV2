package i.flowers.database.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "files"
)
@Getter
@Setter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    @Column(unique = true)
    private String name;

    public FileEntity(Long id, byte[] data, String name) {
        this.id = id;
        this.data = data;
        this.name = name;
    }

    public FileEntity(byte[] data, String name) {
        this.data = data;
        this.name = name;
    }

    public FileEntity() {
    }
}
