# address-book
## Description
Personal address book for day-to-day use, written in Java.

## Usage
Just compile and run with your Java manager of choice

## Architecture
- `persistance` - keeps data after program end
  - `persistance.address` - contains infrmation about address
  - `persistance.contact` - one contact in book, contains `persistance.address`
  - `persistance.contactfields` - enum with all contact available list and its types
  - `persistance.contactmanager` - CRUD api for book, implemented with JSON file format
- `menu` - used to interact with user
  - `menu.menu` - polymorphic class for all menus. Each menu implements at least `show()` and `execute()` methods, which are used to interact with user and further with `manager`
  - `menu.*Menu` - classes for interactin with user, polymorphic childs of `menu`
  
 ## Post
*TODO*
