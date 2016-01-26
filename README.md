## ChannelSync

ChannelSync extends Moqui to synchronize Orders, Products and Categories with various shopping cart systems.

## Supported Shopping Carts
- [Spree Commerce](http://spreecommerce.com/) / [Solidus](http://solidus.io/)

## Setup Commands Quick Reference

Java 8 is recommended: <http://www.oracle.com/technetwork/java/javase/downloads/index.html>

Here are command line steps for initial checkout, setup, and run:

    $ git clone git@github.com:moqui/moqui-framework.git moqui
    $ cd moqui
    $ vi myaddons.xml

Add the following to the myaddons.xml

    <addons>
        <component name="ChannelSync" group="thesquirrelz" version="" branch="master" repository="github-ssh"/><!-- no releases -->
    </addons>

Now you can install the component

    $ ./gradlew getComponent -Pcomponent=ChannelSync
    $ ./gradlew load
    $ ./gradlew run

Here are steps for a basic update:

    $ cd moqui
    $ ./gradlew cleanAll gitPullAll load
    $ ./gradlew run

## TODO
- Sync Products from Moqui to Spree
- Sync Inventory changes from Moqui to Spree
- Sync Categories from Moqui to Spree
- Sync Orders from Spree to Moqui

