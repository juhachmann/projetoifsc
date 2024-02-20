package com.github.juhachmann.estagios;
/**
 * There are 4 possible relations in JPA, that would be translated like this in DB:
 *
 * - OneToOne : the second part of the relation receives the foreign key, that must be unique
 *
 * - OneToMany : the second part receives the foreign key, that must NOT be unique
 *
 * - ManyToOne : is the inverse of OneToMany, where the foreign key will live
 *
 * - ManyToMany : is the relation that would be normalised with an intermediate table in DB, that would receive
 * both entities foreign key, which would NOT be unique
 *
 */

import jakarta.persistence.*;

import java.util.List;

/**
 * ONE TO ONE
 *
 * Example: A User has only one unique Address, which is stored in another table
 *
 */

@Entity
class User {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "user")
    private Address address;
}

@Entity
class Address {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;
}


/**
 * ONE TO MANY AND MANY TO ONE
 *
 * Example : an author writes many articles, but each article belongs to only one author
 */

@Entity
class Author {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "author")
    private List<Article> articles; // This will not be a column, instead, it will search for rows in "Article" equivalent table
}

@Entity
class Article {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Author author;
}

/**
 * MANY TO MANY
 *
 * Example : a course may have many students and a student may take many courses
 */

@Entity
class Course {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
//    @JoinTable(
//            name = "course_student",
//            joinColumns = @JoinColumn(name = "course_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "student_id", nullable = false)
//    )
    private List<Student> students;
}

@Entity
class Student {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}

/**
 * Many To Many Unidirectional
 *
 * START FROM THIS EXAMPLE!
 *
 * Example : a teacher may study many areas, and a area is studied by many teachers, but the area does not need to know its "teachers" list*
 */

@Entity
class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Area> areas;
}

@Entity
class Area {
    @Id
    @GeneratedValue
    private Long id;
}


// EAGER and LAZY Loading
// OneToOne and ManyToOne are eager
// OneToMany and ManyToMany are Lazy (only fetches the data from database when explicitly asks to)

// Put the examples of sql

// Optional settings

// Link to Documentation
// https://jakartaee.github.io/persistence/latest/api/jakarta.persistence/module-summary.html