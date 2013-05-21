Manufacturing
=============

Java demo for Layers, Facade, Strategy patterns

Class Definitions
-------------------

• UIFacade - UserInterface that gives you two options; To run an automatic test (which creates a manual run and 3 recipe runs), and go to the machine control (create custom control values or choose recipes).

• UserInterface - UserInterface where you can customize the manufacturing by setting control values.

• MachineControlFacade- Controls the recipe and makes calls to determine if part is good/bad.

• MachineControl - Has the algorithms to prepare recipes and determine if part is good/bad.

• Recipes - This is the strategy class. It has definitions for 3 recipies- ConstantPressure, ConstantCurrent, and Ramp recipes.

• HardwareFacade - calls to set control values through the Hardware layer.

• Hardware - Performs the manufacturing of parts (writes to a DAS file).
