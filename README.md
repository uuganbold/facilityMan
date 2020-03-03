# Facility Management System

Design and development by Tyler Arndt, Sam Siner and Uuganbold Tsegmed

## Project 1

#### How to Run

The program can be run with the "mvn spring-boot:run" command. Alternatively, the FacilityManApplication file contains a main method that can be run directly from the code editor.

#### Project Description

In this project, we will start providing an object domain model for Facility Management System by addressing the design  
and implementation using OOP. The aim is to have a domain model that is designed using OOP that will be used to study  
Object relationship and Dependency.  

#### Approach

Our first task was to study the existing facility management system to determine how we might improve on its design.  
We discovered the original design had poor separation of concerns and by focusing on the Interface Segregation Principle,  
we could achieve better SoC.

Instead of replicating the original three interfaces, we split them into six:

1. FaclityInformationService
2. FacilityInspectionService
4. FacilityInventoryService
4. FacilityUseService
5. MaintenanceService
6. MaintenanceRequestService

In addition, we decided upon a few other key design considerations:
1. A "Facility" object could either be a Building or a Unit. A Building holds a list of Units via the composite pattern.
2. A Schedule object holds a start date, end date, and a method returning the number of days between them.
3. Our DAO objects currently hold the data sources, which are either an ArrayList or HashMap, respectively.
4. For now, the system client is the FacilityManApplication.java file.



