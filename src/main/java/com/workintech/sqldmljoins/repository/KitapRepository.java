package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitapRepository extends JpaRepository<Kitap, Long> {

    // Dram ve Hikaye türündeki kitapları listeleyin. JOIN kullanmadan yapın (List books of genre Dram or Hikaye)
    String QUESTION_1 = "SELECT * FROM kitap k WHERE k.turno IN (SELECT t.turno FROM tur t WHERE t.ad IN ('Dram', 'Hikaye'))";
    @Query(value = QUESTION_1, nativeQuery = true)
    List<Kitap> findBooks();

    // Kitapların ortalama puanını bulun (Find the average score of all books)
    String QUESTION_10 = "SELECT AVG(k.puan) FROM kitap k";
    @Query(value = QUESTION_10, nativeQuery = true)
    Double findAvgPointOfBooks();
}
