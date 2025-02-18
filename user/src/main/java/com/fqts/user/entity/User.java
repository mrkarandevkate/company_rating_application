    package com.fqts.user.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;

    @Entity
    @Table(name = "users")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int userID;

        @Column(nullable = false)
        private String name;
        @Column(unique = true,nullable = false)
        private String email;
        @Column(nullable = false)
        private String password;
        private String role;
    //    @Enumerated(EnumType.STRING)
    //    private Status is_active=Status.NOTALLOWED;
        private String status;

        @Temporal(TemporalType.TIMESTAMP)
        private Date created_At= new Date();


    }
