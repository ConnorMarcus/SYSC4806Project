package sysc4806.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sysc4806.project.models.Project;


@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findById(long id);
}
