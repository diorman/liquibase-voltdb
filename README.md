liquibase-voltdb [![Release](https://jitpack.io/v/diorman/liquibase-voltdb.svg)](https://jitpack.io/#diorman/liquibase-voltdb)
==================


Liquibase extension for VoltDB. It supports SQL changesets only

How to
------
Check [here](https://jitpack.io/#diorman/liquibase-voltdb) for instructions

Update classes
--------------

In order to call @UpdateClasses procedure form within a changeset you have to define a custom change

```xml

<changeSet id="the-id" author="the-author">
    <customChange class="liquibase.ext.voltdb.change.UpdateClasses" jarFile="path/to/file.jar" />
</changeSet>

```

and if you want to remove classes


```xml

<changeSet id="the-id" author="the-author">
    <customChange class="liquibase.ext.voltdb.change.UpdateClasses" classSelector="org.mycompany.utils.*,*.DebugHandler" />
</changeSet>

```
