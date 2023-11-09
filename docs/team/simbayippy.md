---
layout: default.md
title: "Yip Sin Hangs's Project Portfolio Page"
---

# Project: ClinicAssistant

## Overview
ClinicAssistant is a desktop application used by General Practitioner clinics' admin staff for patient contact management. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Summary of Contributions
Given below are my contributions to the project.

### Code contributed
Code contributed: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=simbayippy&breakdown=true)

**New Feature:** Implemented the Appointment class and introduced the add appointment feature (Pull
  request [#46](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/46/))
- What it does: Enables users to effortlessly schedule and track appointments for patients through a new `appt` command.
  Users specify key details such as description, date, and time of the appointment which enhancing the 
  application's functionality.
- Justification: The new feature brings about user convenience, by simplifying the process of adding appointments with 
  an easy-to-use command `appt`. This user-friendly approach enhances the overall usability of the application,
  allowing users to manage appointments seamlessly.

**New Feature:** Implemented appointment editing capability (Pull request 
  [#57](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/57))
- What it does: Allows users to modify the details of an existing appointment through the `edit_appt` command. Users 
  can specify the appointment index and update the description, date, or both.
- Justification: This new feature brings about flexible appointment management, empowering users to adapt a patients 
  appointments as needed by providing the ability to edit key details. This flexibility enhances the overall user 
  experience and accommodates changes in scheduling or appointment information.

**New Feature:** Implemented finding appointment by patient's name (Pull Request
  [#72](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/72))
- What it does: Introduces the ability to filter the displayed appointment list based on a person's name through the 
  `find_appt` command. Users can input a name, and the system will show only appointments associated with the 
  specified person.
- Justification: This feature brings about enhanced user visibility, by providing a targeted way to view appointments 
  associated with a specific person. Users can easily focus on the relevant information, streamlining their 
  interaction with the application.

### Enhancements implemented
**Enhancements to existing features:** Fixed bugs coming from PE-D (Pull Request 
  [#166](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/166))
- Fixed a total of 16 bugs found from PE-D
- Primarily included UI bugs found due to interaction errors between editing of patients and their associated appointments/
- Fixed integration issues between classes of Doctor, Appointments and Timeslots.
- Fixed success message displayed for associated appointment commands of `appt` and `edit_appt`

### Contributions to the UG
- Added and updated documentation for the features `appt`, `edit_appt`, `edit` `find_appt`.
  (Pull request [#110](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/110/)

### Contributions to the DG
- Added documentation for `add`, `delete` and `edit_appt` feature. (Pull request [#102](https://github.com/AY2324S1-CS2103T-W09-3/tp/pull/102))

### Community
- Regularly reviewed teammates PR's, as well as highlighting of bugs and its appropriate fixes.
- Pull Requests reviewed: 
  30 [(Github)](https://github.com/AY2324S1-CS2103T-W09-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Asimbayippy)

### Contributions beyond the project team
- Found and reported bugs for [CS2103T-W12-4](https://github.com/AY2324S1-CS2103T-W12-4/tp/issues) during PE-D