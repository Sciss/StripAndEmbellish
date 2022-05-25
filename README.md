# Strip and Embellish

[![Build Status](https://github.com/Sciss/StripAndEmbellish/workflows/Scala%20CI/badge.svg?branch=main)](https://github.com/Sciss/StripAndEmbellish/actions?query=workflow%3A%22Scala+CI%22)

This project contains my personal set-up for a live improvisation project.
It is an extension of [Wolkenpumpe](https://codeberg.org/sciss/Wolkenpumpe).

All code here
is (C)opyright 2022 by Hanns Holger Rutz. All rights reserved. This project is released under the
[GNU General Public License](https://codeberg.org/sciss/StripAndEmbellish/raw/main/LICENSE) v3+ and comes with absolutely no warranties.
To contact the author, send an e-mail to `contact at sciss.de`.

## building

Builds with sbt against Scala 2.13. Use `sbt run`, or `sbt assembly` to create a self-contained jar (you may need the
latter if you want to add the tablet library to the system's class path).

## running

To use the Wacom controls, add environment variable `LD_LIBRARY_PATH=lib` to the JVM run
(currently only Linux native library included).
