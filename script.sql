--mysql -u root -p

CREATE DATABASE events_ces3; --Crear BD

USE events_ces3;


DROP TABLE IF EXISTS event_attendees;
DROP TABLE IF EXISTS attendees;
DROP TABLE IF EXISTS events;


CREATE TABLE events(
    id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    location VARCHAR(255) NOT NULL
); 


CREATE TABLE attendees(
    document VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    first_last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(100)
); 

-- CREATE TABLE event_attendees(
--     doc_attendees VARCHAR(20) FOREIGN KEY REFERENCES attendees(document),
--     event_id INT(5) FOREIGN KEY REFERENCES events(id),
--     register_date DATE NOT NULL DEFAULT CURRENT_DATE,
--     PRIMARY KEY(doc_attendees, event_id)
-- ); 