---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ClinicAssistant User Guide

ClinicAssistant is a desktop app that offers seamless patient contact management for GP clinics’ admin staff who are familiar with Command Line Interface (CLI), 
optimized for use via a CLI while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, ClinicAssistant can get your patient record management tasks done faster than traditional pen-and-paper methods. 
It ensures efficient adding, editing and finding of patient information, optimising clinic operations.
<!-- * Table of Contents -->
- **Introduction**
    - [Target Audience](#target-audience)
    - [How to Use the Guide](#how-to-use-the-guide)

- **Getting Started**
    - [Quick start](#quick-start)

- **Features and Commands**
    - [Adding a person: `add`](#adding-a-person--add)
    - [Adding a doctor: `add_doctor`](#adding-a-doctor--adddoctor)
    - [Adding an appointment: `appt`](#adding-an-appointment--appt)
    - [Listing all patients : `list`](#listing-all-patients--list)
    - [Listing all appointments : `list_appt`](#listing-all-appointments--listappt)
    - [Editing a patient: `edit`](#editing-a-patient--edit)
    - [Editing an appointment: `edit_appt`](#editing-an-appointment--editappt)
    - [Deleting a patient : `delete`](#deleting-a-patient--delete)
    - [Deleting a doctor : `delete_doctor`](#deleting-a-doctor--deletedoctor)
    - [Deleting an appointment : `delete_appt`](#deleting-an-appointment--deleteappt)
    - [Finding patient details by name: `find`](#finding-patient-details-by-name--find)
    - [Finding patient details by nric: `find_nric`](#finding-patient-details-by-nric--findnric)
    - [Finding appointment by patient name or date: `find_appt`](#finding-appointment-by-patient-name-or-date--findappt)
    - [Viewing available timeslots : `view`](#viewing-available-timeslots--view)
    - [Clearing all patients : `clear`](#clearing-all-patients--clear)
    - [Exiting the program : `exit`](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)

- **Additional Information**
    - [FAQ](#faq)
    - [Known issues](#known-issues)
    - [Command summary](#command-summary)

<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

### Target Audience

// to fill in

### How to use the guide

|   If you are   |                                               You should                                                |
|:--------------:|:-------------------------------------------------------------------------------------------------------:|
|   a new user   | proceed to our [quick start](#quick-start) section to set up the necessary details and try our features |
| returning user |      skip to our [command summary](#command-summary) for an in-depth summary of all our features!       |

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `clinicassistant.jar` from [here](https://github.com/AY2324S1-CS2103T-W09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ClinicAssistant.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar clinicassistant.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients in the ClinicAssistant.

   * `add /n John Doe /p 98765432 /ejohnd@example.com /g M /age 22 /eth Chinese /ic T1234567E a/John street, block 123, #01-01 /t allergy` :
   Adds a patient named `John Doe` to ClinicAssistant the specified details such as male `M`, ethnicity `Chinese` and IC number `T1234567E`.

   * `delete 3` : Deletes the 3rd patient shown in the current list of ClinicAssistant.
   * `appt /for 1 /d description details /on 02-01-2024 12:00` : Adds an appointment with specified time to the patient identified with `INDEX` 1 in the list.

   * `clear` : Deletes all patients.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the compulsory parameters to be supplied by the user.<br>
  e.g. in `add /n NAME`, `NAME` is a parameter which can be used as `add /n John Doe`.

* Items in square brackets are optional.<br>
  e.g `/n NAME [/t TAG]` can be used as `/n John Doe /t smoker` or as `/n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[/t TAG]…​` can be used as ` ` (i.e. 0 times), `/t smoker`, `/t torn ACL /t diabetes` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `/n NAME /p PHONE_NUMBER`, `/p PHONE_NUMBER /n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* All commands are case-sensitive. <br>
  e.g. `delete 1` will work, but `Delete 1` or `DELETE 1` will not work.

* **If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.**
</box>


### Adding a person: `add`

Adds a patient to the database.

Format: `add /n NAME /p PHONE_NUMBER /e EMAIL /g GENDER /a AGE /eth ETHNICITY /ic NRIC /a ADDRESS [/t TAG]…​`
* A patient has a name, phone number, email, gender, age, ethnicity, NRIC, address and optional tags.

<box type="info" seamless>

**Note:**
* A patient must have all parameters to be valid. <br>
e.g. `add /n Drizzy`, `add Drizzy /p 99090909` or any inputs with missing parameters will not work.
  </box>

Examples:
* Valid input: `add /n Drizzy /p 99090909 /e drake@gmail.com /g F /age 18 /eth Chinese /ic T0123456E /a 901 Shelby Dr`
  * Output (success): <br>
    `New person added: Drizzy | Phone: 99090909 | Email: drake@gmail.com | Gender: F | Age: 18 | Ethnic: Chinese | NRIC: T0123456E | Address: 901 Shelby Dr | Tags:` 
* Invalid input: `add /n Drizzy`
  * Output (failure): <br>
  `Invalid command format!` <br>
  `add: Adds a person to the address book. Parameters: /n NAME /p PHONE /e EMAIL /g GENDER /age AGE /eth ETHNICITY /ic NRIC NO. /a ADDRESS [/t TAG]...`

### Adding a doctor: `add_doctor`

Adds a doctor to the database. A doctor has a name, phone number, email, gender, age, and address.

Format: `add_doctor /n NAME /p PHONE_NUMBER /e EMAIL /g GENDER /age AGE /a ADDRESS`

* A doctor has a name, phone number, email, gender, age, address and they are all mandatory.

<box type="info" seamless>

**Note:**
* Doctor's names are case-sensitive.
* You cannot add two Doctors with the same name.
  * Adding two doctors with the names `John Doe` and `John doe` is allowed.
  * Adding two doctors with the names `John Doe` and `John Doe` is not allowed.
</box>

<box type="warning" seamless>

**Warning:**
* Make sure the details of the doctor is correct. As of now, you cannot edit a doctor's details yet.
* To change a doctor's details, you have to first delete the doctor, then re-enter his/her details.
    * Before deleting a doctor, you must make sure he/she does not have any pre-existing appointments. 
  Deleting a doctor deletes his/her appointments too.
</box>

Examples:
* Valid input: `add_doctor /n Dr Lee /p 81824444 /e drlee@gmail.com /g M /age 30 /a 901 Shelby Drive`
  * Output (success): <br>
  `New Doctor added: Dr Lee | Phone: 81824444 | Email: drlee@gmail.com | Gender: M | Age: 30 | Address: 901 Shelby Drive`
* Invalid input: `add_doctor /n Dr Lee`
  * Output (failure): <br>
  `Invalid command format!` <br>
    `add_doctor: Adds a Doctor to clinic assistant.` <br>
    `Parameters: /n NAME /p PHONE /e EMAIL /g GENDER /age AGE /a ADDRESS` <br>
    `Example: add_doctor /n John Doe /p 98765432 /e johnd@example.com /g M /age 22 /a 311, Clementi Ave 2, #02-25` <br>

### Adding an appointment: `appt`

Adds a new appointment for the specified patient. An appointment requires the patient's index, the doctor's index, a description, and the appointment date and time.

Format: `appt /for PATIENT_INDEX /doc DOCTOR_INDEX /d DESCRIPTION /on DATE_TIME`

* `PATIENT_INDEX`: Index of the patient for whom the appointment is being made.
* `DOCTOR_INDEX`: Index of the doctor who will handle the appointment.
* `DESCRIPTION`: Brief description of the appointment.
* `DATE_TIME`: Date and time of the appointment in the format dd-MM-yyyy HH:mm (e.g., 01-01-2024 12:00).

<box type="info" seamless>

**Note:**
* The doctor's index must correspond to a valid doctor previously added to the system.
* The appointment's date and time must be in the future.
* All fields (patient index, doctor index, description, and date/time) are mandatory.
</box>

<box type="warning" seamless>

**Warning:**
* Only one appointment is allowed per timeslot. i.e. 2 doctors cannot have appointments on the same timeslot. This is a current limitation that we are working on as part of our future implementations.
</box>

Examples:
* Valid input: `appt /for 1 /doc 2 /d Blood test /on 01-01-2024 12:00`
    * Output (success): <br>
      `New appointment added | Patient: Alex Yeoh | Description: Blood test | Date: 01 Jan 2024 12.00 PM`
* Invalid input 1 (no description provided): `appt /for 1 /doc 2 /on 01-01-2024 12:00`
    * Output (failure): <br>
      `Invalid command format! ` <br>
      `appt: Adds a appointment to the patient identified by the index number used in the displayed patient list.` <br>
      `Parameters: /for PATIENT_INDEX /doc DOCTOR_INDEX /d DESCRIPTION /on DATE_TIME` <br>
      `Example: appt /for 1 /doc 1 /d x-ray scan /on 02-01-2024 12:00`<br>
* Invalid input 2 (invalid DATE_TIME): `appt /for 1 /doc 2 /d Blood test /on tuesday`
    * Output (failure): <br>
      `Date must be in dd-MM-yyyy HH:mm format.` <br>
      `Date & time must be after the current time.` <br>
      `Time of appointment must be on the hour, between 9 AM and 5 PM inclusive.` <br>

### Listing all patients : `list`

Shows a list of all people in the database.

Format: `list`

Examples:
* Valid input: list
  * Output (success): <br>
  `Listed all patients`


### Listing all patients : `list_appt`

Shows a list of all appointments in Clinic Assistant.

Format: `list_appt`

### Editing a patient: `edit`

Edits an existing patient from the Clinic Records.

Format: `edit INDEX [/n NAME] [/p PHONE] [/e EMAIL] [/g GENDER] [/age AGE] [/eth ETHNIC] [/ic NRIC] [/a ADDRESS] [t/TAG]…​`

* Edits the patient at the specified INDEX. 
The index refers to the index number shown in the displayed patient list. 

<box type="info" seamless>

**Note:**
* The index **must be a positive integer** 1, 2, 3, …​ 
* At least one of the optional fields must be provided. 
* Existing values will be updated to the input values. 
* When editing tags, the existing tags will be removed ie adding of tags is not cumulative. 
* You can remove all the person’s tags by typing `/t` without specifying any tags after it. 
</box>

Examples:
* Valid input: `edit 1 /p 91234567 /e johndoe@example.com`
    * Output (success): <br>
  `Edited Person: Alex Yeoh | Phone: 91234567 | Email: johndoe@example.com | Gender: M | Age: 21 | Ethnic: Chinese | NRIC: T1341367E | Address: Blk 30 Geylang Street 29, #06-40 | Tags:`

* Valid input: `edit 2 /n Betsy Crower /t`
  * Output (success): <br>
  `Edited Person: Betsy Crower | Phone: 99272758 | Email: berniceyu@example.com | Gender: F | Age: 16 | Ethnic: Chinese | NRIC: T1231437E | Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18 | Tags: `

### Editing an appointment: `edit_appt`

Edits an existing appointment from the Clinic Records.

Format: `edit_appt INDEX [/d DESCRIPTION] [/on DATE_TIME]`

* Edits the appointment at the specified `INDEX`. The index refers to the index number shown in the displayed appointment list.
* **At least one** field must be edited.
* Existing values will be updated to the input values.

<box type="info" seamless>

**Note:**
* The index **must be a positive integer** 1, 2, 3, …​
* Doctors of the appointment cannot be edited, planned addition in future implementations.
</box>

Examples:
* Valid input: `edit_appt 1 /d changed to x-ray scan /on 01-01-2024 09:00`
    * Output (success): <br>
      `Newly edited appointment | Patient: Alex Yeoh | Description: changed to x-ray scan | Date: 01 Jan 2024 09.00 AM`
* Invalid input 1 (no fields provided): `edit_appt 1`
    * Output (failure): <br>
      `At least one field to edit must be provided: [/d DESCRIPTION][/on DATE_TIME]` <br>
      `Example: edit_appt 1 /on 01-01-2024 00:00` <br>
* Invalid input 2 (invalid date & time): `edit_appt 1 /on tuesday`
    * Output (failure): <br>
      `Date must be in dd-MM-yyyy HH:mm format.` <br>
      `Date & time must be after the current time.` <br>
      `Time of appointment must be on the hour, between 9 AM and 5 PM inclusive.` <br>

### Deleting a patient : `delete`

Deletes a patient from the clinic’s registra via the specified index.

Format: `delete INDEX`
* Deletes the person at the specified `INDEX`
* Index refers to the index number shown on the displayed person list. 
* User could use list to display all the patients in the registra first

<box type="info" seamless>

**Note:**
* The index **must be a positive integer** 1, 2, 3, …​
</box>

Examples:
* Valid input: `delete 2`
  * Output (success): <br>
  `Deleted Person: Bernice Yu | Phone: 99272758 | Email: berniceyu@example.com | Gender: F | Age: 16 | Ethnic: Chinese | NRIC: T1231437E | Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18 | Tags: [throat infection][on antibiotics] from clinic records`
* Invalid input: `delete 0`
  * Output (failure): <br>
  `Invalid command format!` <br>
`delete: Deletes the patient with the index number used in the clinic records.` <br>
    `Parameters: INDEX (must be a positive integer in the list)` <br>
    `Example: delete 1`

### Deleting a doctor : `delete_doctor`

Deletes the specified doctor from the Clinic Records.

Format: `delete_doctor INDEX`

* Deletes the doctor at the specified `INDEX`.
* `INDEX` refers to the index number shown on the displayed doctor list.
* User could first click on the doctor tab to show the doctor list.


<box type="info" seamless>

**Note:**
* The command is case-sensitive. E.g. `Delete_doctor INDEX` will not work
* The index **must be a positive integer** 1, 2, 3, …​
  </box>
 
<box type="warning" seamless>

 **Warning:**
 * Before deleting a doctor, you must make sure he/she does not have any pre-existing appointments.
Deleting a doctor deletes his/her appointments too.
 </box>




Examples:<br>
initial Doctor list:
![Add_Doctor](images/Add_Doctor.png)
* Valid input: `delete_doctor 1`
    * Output (success): <br>
      `Deleted Doctor: John Doe | Phone: 98765432 | Email: johnd@example.com | Gender: M | Age: 22 | Address: 311, Clementi Ave 2, #02-25 from clinic records`
* Invalid input: `delete_doctor 0` for non positive integers or no input
    * Output (failure): <br>
      `Invalid command format!`<br>
      `delete_doctor: Deletes the doctor with the index number used in the clinic records.`<br>
      `Parameters: INDEX (must be a positive integer in the list)`<br>
      `Example: delete_doctor 1`<br>
* Invalid input: `delete_doctor 2` for positive integers out of bounds
    * Output (failure): <br>
      `The doctor index provided is invalid`


### Deleting an appointment : `delete_appt`

Deletes an appointment from the clinic’s registra via the specified index.

Format: `delete_appt INDEX`
* Deletes the appointment at the specified `INDEX`
* `INDEX` refers to the index number shown on the displayed appointment list.
* User could use `list_appt` to display all the appointments in the registra first

<box type="info" seamless>

**Note:**
* The command is case-sensitive. E.g. `Delete_appt INDEX` will not work
* The index **must be a positive integer** 1, 2, 3, …​
</box>

Examples:<br>
initial Appointment list:
![Add_Appointment](images/Add_Appointment.png)
* Valid input: `delete_appt 1`
  * Output (success): <br>
  `Deleted Appointment seedu.address.model.appointment.Appointment{description=description details, dateTime=2024-01-02T12:00} of Alex Yeoh | Phone: 87438807 | Email: alexyeoh@example.com | Gender: M | Age: 21 | Ethnic: Chinese | NRIC: T1341367E | Address: Blk 30 Geylang Street 29, #06-40 | Tags: [friends]`
* Invalid input: `delete_appt 0` for non-positive integers or no input
  * Output (failure): <br>
    `Invalid command format:` <br>
    `delete_appt: Deletes the Appointment identified by the index number used in the displayed appointments list.` <br>
    `Parameters: INDEX (must be a positive integer) Example: delete_appt 1`<br>
* Invalid input: `delete_appt 2` for positive integers out of bounds
  * Output (failure): <br>
  `The patient index provided is invalid`


### Finding patient details by name: `find`

Finds patients whose name contains the given keyword.

Format: `find KEYWORD`
* Only the name is searched for.

<box type="info" seamless>

**Note:**
* The search is case-insensitive. e.g `hans` will match `Hans`.
</box>

Examples:
* Valid input: `find Alex`
    * Output (success): <br>
    `1 patient found!`
* Invalid input: `find 123`
    * Output (failure): <br>
    `No patients found!`


### Finding patient details by nric: `find_nric`

Finds the patient who has the given NRIC.

Format: `find_nric NRIC`

<box type="info" seamless>

**Note:**
* The search is not case-sensitive. ie the NRIC `T1234567E` is equivalent to the NRIC `t1234567e`. <br>
* The inputted NRIC must be valid. The NRIC must start with 'S' or 'T' and contain 7 digits between the 2 alphabets.
ie `V1234567E` and `S123456eE` are invalid NRICs
</box>

Examples:
* Valid Input: `find_nric S0123456N`
    * Output (success): <br>
    `1 patient found!`
* Valid input: `find_nric t1234567e`
    * Output (failure, if no patient with this NRIC exists in the list): <br>
  `No patients found!`
* Invalid input: `find_nric S123v456X`
    * Output (failure, wrong NRIC format): <br>
    `Invalid command format!` <br>
    `NRICs should contain 7 digits, with S or T at the beginning and a letter at the end`

### Finding appointment by patient name or date: `find_appt`

Finds appointments of a patient whose name contains the given keyword and/or appointment falls on the date given.

Format: `find_appt [/n KEYWORDS] [/on DATE]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Date is in the format dd-MM-yyyy e.g. 01-01-2024.

**Note:**
Although both fields are optional, at least 1 must be given. e.g. `find_appt` with no field inputs will not work

Examples:
* `find_appt /n John /on 01-01-2024 ` returns appointments of patient with name `John` that falls on 1 Jan 2024.

### Viewing available timeslots : `view`

Displays all **available** timeslots on the specified date by the user.

Format: `view /on DATE`

* Displays all available timeslots that can be booked on the `DATE`.
* The `DATE` **must be a valid date on the calendar in the form `dd-MM-yyyy` exactly**
* Timeslots for appointments are fixed at 1 hour each, **starting from 9AM to 5PM**
* Any Timeslot that is displayed after calling `view /on DATE` can be booked

Examples:
* `view /on 02-01-2024` shows all available timeslots that can be booked on 02 Jan 2024.
* If timeslot `10 AM` is displayed, one can proceed to book an appointment for that `DATE` at `10 AM`

**Note:**
* There should not be any timings after `DATE`. e.g. `dd-MM-yyyy 18:00` will return an error.
* The `DATE` should be entered in exactly `dd-MM-yyyy` format separated by a `-` and not anything else such as `/`…​

### Clearing all patients : `clear`

Clears all patients from ClinicAssistant.

Format: `clear`

### Exiting the program : `exit`

Exits ClinicAssistant.

Format: `exit`

### Saving the data

ClinicAssistant data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ClinicAssistant data are saved automatically as a JSON file `[JAR file location]/data/clinicassistant.json`. Advanced users are welcome to update data directly by editing that data file.

**Caution:**
If your changes to the data file makes its format invalid, ClinicAssistant will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClinicAssistant home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add /n NAME /p PHONE_NUMBER /e EMAIL /g GENDER /age AGE /eth ETHNICITY /ic NRIC /a ADDRESS [/t TAG]…​` <br> e.g., `add /n James Ho /p 81808888 /e jamesho@example.com /g M /age 22 /eth Chinese /ic T1234567E /a 123, Clementi Rd, 1234665 /t allergic to dust`
**Add Doctor**  | `add_doctor /n NAME /P PHONE_NUMBER /e EMAIL /g GENDER /age AGE /a ADDRESS` <br> e.g., `add_doctor /n John Doe /p 98765432 /e johnd@example.com /g M /age 22 /a 311, Clementi Ave 2, #02-25`
**Add Appointment**  | `appt /for PATIENT_INDEX /doc DOCTOR_INDEX /d DESCRIPTION /on DATE_TIME` <br> e.g., `appt /for 1 /doc 1 /d x-ray scan /on 02-01-2024 12:00`
**Edit**   | `edit INDEX [/n NAME] [/p PHONE] [/e EMAIL] [/g GENDER] [/age AGE] [/eth ETHNIC] [/ic NRIC] [/a ADDRESS] [/t TAG]…​` <br> e.g., `edit 1 /a 3 NUS Computing Drive`
**Edit Appointment** | `edit_appt INDEX [/d DESCRIPTION] [/on DATE_TIME]` <br> e.g., `edit_appt 1 /d changed to x-ray scan`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Delete Doctor** | `delete_doctor INDEX`<br> e.g, `delete_doctor 3`
**Delete Appointment** | `delete_appt INDEX` <br> e.g., `delete_appt 3`
**Find**   | `find KEYWORD`<br> e.g., `find john`
**Find by NRIC**   | `find_nric NRIC` <br> e.g., `find_nric T1234567E`
**Find Appointment** | `find_appt [/n KEYWORD] [/on DATE]`<br>e.g., `find_appt /n John /on 01-01-2024 12:00`
**List**   | `list`
**View**   | `view /on DATE`<br> e.g., `view /on 02-01-2024`
**Help**   | `help`
