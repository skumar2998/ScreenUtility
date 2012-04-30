##Android Screen Utility
=

A utility class for dynamically setting view object height and width based on percentages of the total screen height and width.

This has saved me a lot of time creating layouts that look good across different screen sizes, but it doesn't look very good on larger devices such as tablets.

##Using this class

1. Add the class to your package and change the package line at the top:

```Java

package com.my.package;

```

2. Create an instance of this class:

```Java

	ScreenUtility utility = new ScreenUtility(this);

```
(We need to pass the current activity to it in the constructor)

3. Declare your view objects, for example a button:

```Java

Button myButton = (Button)findViewById(R.id.mybutton);

```

4. Set its dimensions!

```Java

 int width = 100;
 int height = 20;
 utility.setViewObjectDimensionsAsPercentage(height, width, myButton);

```

The view object will now be the size you gave it, in percentage, of the total height and width of the screen size.
