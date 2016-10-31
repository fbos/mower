# Simple lawn-mower


### Conception


-   the program assumes that an object can be blocked by an other object if it's at the same position on the area.
    If such blocking is not required, then replace class com.fbos.mower.geom.FilledArea
    by superclass Area from same package.

-   the program assumes that only sequential mode is required by default. However, a parallel mode is also
    implemented in class com.fbos.mower.Executor.
    
### Tests

Tests can be run with maven command