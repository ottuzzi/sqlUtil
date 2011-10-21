sqlUtil
=======

sqlUtil is a simple library to access RDBMS. Actually is compatible with JDK 1.4 and
does not depend on any specific JDBC driver. 

Highlights
----------

The following SQL statement are actually implemented through specific methods:

* insert
* update
* delete
* select
** simpleSelect -- a select that returns a single value
** dynaSelect -- a select that returns a resultSet wrapped in List of DynaBeans
* executeSP -- to execute StoreProcedure

Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b my_sqlUtil`)
3. Commit your changes (`git commit -am "Added Snarkdown"`)
4. Push to the branch (`git push origin my_sqlUtil`)
5. Create an [Issue][1] with a link to your branch
6. Enjoy a refreshing Diet Coke and wait

[1]: http://github.com/ottuzzi/sqlUtil/issues