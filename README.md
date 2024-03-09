### Hexlet tests and linter status:

##**Console application that finds the difference between files**
_The code describes the operation of a console application whose task is to find the difference between two received
files in json or yaml formats, and then outputs the found difference in the form of a new file, the format of which the
user chooses from three possible options:_
⋅⋅ by default - format "stylish" - text format, where changes are indicated by signs: "+" added, "-" deleted, and just "
space" - the file remains unchanged
The user can choose from 2 more formats:
⋅⋅ "plain" format - text format, where differences in the file are described in words
⋅⋅ "json" format - json format, where changes are presented in the key:value format, where the value is presented
complexly - change status, old value and new value

To work with the application, the user needs to enter into the console the path to the location of the files that need
to be compared and additionally write the desired format for outputting the differences

[![Actions Status](https://github.com/DariaKarpova3108/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/DariaKarpova3108/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/bfd279cd76e94335bf76/maintainability)](https://codeclimate.com/github/DariaKarpova3108/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/bfd279cd76e94335bf76/test_coverage)](https://codeclimate.com/github/DariaKarpova3108/java-project-71/test_coverage)

##**Below are the askinems demonstrating the operation of the console application:**
_View help information for program arguments and options_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/tiQOr54LpIbSC8pRGzaGZSeeC})

_View a comparison of two json files without specifying the output format (by default it will output information in stylish format)_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/N6cRT0UqHYNxPJf4yS39743dD})

_View a comparison of two yml files without specifying the output format (by default it will output information in stylish format)_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/prvqXHQFgLkE3RNOKmPmFJGod})

_View a comparison of two json files with an explicit indication of the format - plain text format_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/GB2ZIdShunrEp3caN38VArWMo})

_View a comparison of two yml files with an explicit indication of the format - plain text format_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/vJFEnXrGdlgZhQCTRtwZn2w1M})

_View a comparison of two json files, explicitly specifying the json output_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/osRPy9BGOBBl4Awm8MBoJc6V4})

_View a comparison of two yml files, explicitly specifying the json output_
[![asciicast]({ссылка}.svg)]({https://asciinema.org/a/3D3keXKZYO8fX19yyvaDauoGf})