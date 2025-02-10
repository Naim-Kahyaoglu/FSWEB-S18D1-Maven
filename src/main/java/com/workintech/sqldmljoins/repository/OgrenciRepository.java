package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    // Kitap alan öğrencilerin öğrenci bilgilerini listeleyin (List students who took books)
    String QUESTION_2 = "SELECT o.* FROM ogrenci o WHERE EXISTS (SELECT 1 FROM islem b WHERE b.ogrno = o.ogrno)";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();

    // Kitap almayan öğrencileri listeleyin (List students who didn't take books)
    String QUESTION_3 = "SELECT o.* FROM ogrenci o WHERE NOT EXISTS (SELECT 1 FROM islem b WHERE b.ogrno = o.ogrno)";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    // 10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin
    // (Show students in class 10A or 10B along with the number of books they read)
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.kitapno) AS book_count FROM ogrenci o " +
            "JOIN islem i ON o.ogrno = i.ogrno " +
            "WHERE o.sinif IN ('10A', '10B') " +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    // Öğrenci tablosundaki öğrenci sayısını gösterin (Show the total number of students)
    String QUESTION_5 = "SELECT COUNT(*) FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    // Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz (List distinct student names)
    String QUESTION_6 = "SELECT COUNT(DISTINCT o.ad) FROM ogrenci o";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    // İsme göre öğrenci sayılarının adedini bulunuz (Find the number of students for each name)
    String QUESTION_7 = "SELECT o.ad, COUNT(o.ogrno) AS name_count FROM ogrenci o GROUP BY o.ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    // Sınıfa göre öğrenci sayılarının adedini bulunuz (Find the number of students in each class)
    String QUESTION_8 = "SELECT o.sinif, COUNT(o.ogrno) AS class_count FROM ogrenci o GROUP BY o.sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    // İsim ve soyadlarına göre öğrenci sayılarının adedini bulunuz
    // (Find the number of students by first name and last name)
    String QUESTION_9 = "SELECT o.ad, o.soyad, COUNT(o.ogrno) AS name_surname_count " +
            "FROM ogrenci o GROUP BY o.ad, o.soyad";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
