package web.Dao;

import org.springframework.data.repository.CrudRepository;
import web.models.Role;

public interface RoleDao extends CrudRepository<Role, Long> {

    public Role getOne(Long aLong);
}
