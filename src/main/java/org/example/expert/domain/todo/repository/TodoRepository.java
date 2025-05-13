package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // weather과 date 파라미터 추가
    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(
        @Param("weather") String weather,
        @Param("startDate") LocalTime startDate,
        @Param("endDate") LocalTime endDate,
        Pageable pageable
    );

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    //
    // @Query("SELECT t FROM Todo t " +
    //     "WHERE (:weather IS NULL OR t.weather = :weather)" +
    //     "AND (:startDate IS NULL OR t.modifiedAt >= :startDate)" +
    //     "AND (:endDate IS NULL OR t.modifiedAt <= :endDate)")

}
