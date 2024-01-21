# GUI Java Application for Resource Management

## Dependencies:

During the development of this application, data storage was implemented using json files and a package for it:
 - [json-simple](https://code.google.com/archive/p/json-simple/downloads)

## Functionalities:
- Authorization


Upon launching the application, a login form is presented. After successful authorization, all actions are associated with a specific user, ensuring proper tracking of rights and privileges.

- Deletion and Modification


Users can delete and modify only those resources assigned to them. Access to these operations for other items is restricted through the GUI interface.

- Item Addition


Every user has the capability to add new items. During the addition process, the owner of the item is recorded, allowing subsequent operations such as deletion or modification by the respective user.

- Search Functionality


The application provides a search functionality based on specified criteria. Users can search for items by entering text into the designated field.

- Purchase


Upon making a purchase, the quantity of available items is automatically reduced by the amount purchased, ensuring accurate inventory management.
