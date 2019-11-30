# CustomTextInputLayoutAccessibilityDelegate

- The activity_main.xml contains two TextInputLayout views with a hint of **Pass**.
- The first TextInputLayout uses the default AccessibilityDelegate class (setTextInputAccessibilityDelegate() is not used).
- For the second TextInputLayout a switch is included to simulate the difference between the two cases below at runtime:
  
  **case 1 (Switch is checked):** a new CustomTextInputLayoutAccessibilityDelegate class is applied 
  to TextInputLayout with the code that removes the hint inside onInitializeAccessibilityNodeInfo().
  
  **case 2 (switch is unchecked):** a new CustomTextInputLayoutAccessibilityDelegate class is applied 
          to TextInputLayout with the only super() inside onInitializeAccessibilityNodeInfo().
          
- TalkBack announces: 
   
   **First TextInputLayout:**
   
   password pass editbox...
   
   **Second TextInputLayout:**
   
   case 1: password editbox...
   
   case 2: password pass editbox...

- Output: [https://drive.google.com/open?id=1-AtOAC8k4pj6B0GjCf6i3SHnHI0kDIS3](https://drive.google.com/open?id=1-AtOAC8k4pj6B0GjCf6i3SHnHI0kDIS3)
