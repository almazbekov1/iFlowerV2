package i.flowers.database.repository;


import i.flowers.database.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

    FileEntity findByName(String name);
    FileEntity deleteByName(String name);
}
