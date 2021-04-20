package web.Dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public <S extends Role> S save(S s) {
        entityManager.merge(s);
        return s;
    }

    @Override
    public <S extends Role> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", aLong);
        return Optional.ofNullable(query.getResultList().get(0));
    }

    @Override
    public boolean existsById(Long aLong) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", aLong);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Iterable<Role> findAll() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Iterable<Role> findAllById(Iterable<Long> iterable) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", iterable);
        return query.getResultList();
    }

    @Override
    public long count() {
        List<Role> roles = entityManager.createQuery("select r from Role r").getResultList();
        return roles.stream().count();
    }

    @Override
    public void deleteById(Long aLong) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", aLong);
        Role role = query.getSingleResult();
        entityManager.remove(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {
        for (Role role : iterable) {
            entityManager.remove(role);
        }
    }

    @Override
    public void deleteAll() {
        List<Role> roles = entityManager.createQuery("select r from Role r").getResultList();
        for (Role role : roles) {
            entityManager.remove(role);
        }
    }

    @Override
    public Role getOne(Long aLong) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", aLong);
        return query.getResultList().stream().findAny().orElse(null);
    }
}