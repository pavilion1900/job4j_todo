package ru.job4j.todo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private static final String DELETE_TASK = "DELETE FROM Task t WHERE t.id = :id";

    private static final String FIND_ALL_TASKS = "FROM Task t ORDER BY t.id";

    private static final String FIND_ALL_BY_CONDITION_TASKS = "FROM Task t WHERE t.done = :done ORDER BY t.id";

    private static final String FIND_BY_ID_TASK = "FROM Task t WHERE t.id = :id";

    private final SessionFactory sf;

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public Task replace(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        int result = 0;
        try {
            session.beginTransaction();
            result = session.createQuery(DELETE_TASK)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result > 0;
    }

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(FIND_ALL_TASKS, Task.class);
            tasks = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public List<Task> findAll(boolean done) {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(FIND_ALL_BY_CONDITION_TASKS, Task.class);
            query.setParameter("done", done);
            tasks = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Task result = new Task();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(FIND_BY_ID_TASK, Task.class);
            query.setParameter("id", id);
            result = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(result);
    }
}
