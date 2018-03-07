# Contributing to Arcanum

### Table Of Contents

[Code of Conduct](#code-of-conduct)

[How to contribute](#how-to-contribute)
 * [Environment set up](#environment-set-up)
	 * [ANTLR Plugin for IntelliJ](#antlr-plugin-for-intellij)
	 
[Pull Request Process](#pull-request-process)

[Styleguides](#styleguides)
 * [Java Styleguide](#java-styleguide)
 * [ANTLR Styleguide](#antlr-styleguide)

## Code of Conduct

The Arcanum project is dedicated to providing a harassment-free working environment for all, regardless of gender, sexual orientation, disability, physical appearance, body size, race, or religion. We do not tolerate harassment of any form. All communication should be appropriate for a professional audience including people of many different backgrounds.

Sexual language and imagery is not appropriate for any communication and/or talks. Be kind and do not insult or put down others. Behave professionally.

These are the values to which people in the Arcanum community should aspire:

 * Be friendly and welcoming
 * Be patient
     * Remember that people have varying communication styles and that not everyone is using their native language. (Meaning and tone can be lost in translation.)
 * Be thoughtful
     * Productive communication requires effort. Think about how your words will be interpreted.
     * Remember that sometimes it is best to refrain entirely from commenting.
 * Be respectful
     * In particular, respect differences of opinion.
 * Be charitable
     * Interpret the arguments of others in good faith, do not seek to disagree.
     * When we do disagree, try to understand why.
 * Avoid destructive behavior:
     * Derailing: stay on topic; if you want to talk about something else, start a new conversation.
     * Unconstructive criticism: don't merely decry the current state of affairs; offer—or at least solicit—suggestions as to how things may be improved.
     * Snarking (pithy, unproductive, sniping comments)
     * Discussing potentially offensive or sensitive issues; this all too often leads to unnecessary conflict.
     * Microaggressions: brief and commonplace verbal, behavioral and environmental indignities that communicate hostile, derogatory or negative slights and insults to a person or group.

People are complicated. You should expect to be misunderstood and to misunderstand others; when this inevitably occurs, resist the urge to be defensive or assign blame. Try not to take offense where no offense was intended. Give people the benefit of the doubt. Even if the intent was to provoke, do not rise to it. It is the responsibility of all parties to de-escalate conflict when it arises.


## How to contribute

### Environment set up

First, make sure you have `Java8` or newer and `Maven` installed.
Then execute the following command:

```
git clone https://github.com/Taken0711/arcanum.git
mvn clean install
``` 

#### ANTLR Plugin for IntelliJ

Please use this configuration for the ANTLR plugin:
 * Auto-generate parses upon save : As you want
 * Output directory where all output is generated : Blank
 * Location of imported grammars : Blank
 * Grammar file encoding : Blank
 * Package/namescpace for the generated code:  `net.taken.arcanum.parser`
 * Language (e.g., Java, Python2, CSharp, ...): `Java`
 * ☑ generate parse tree visitor

To generate and the rules :

 * On **ArcanumParser.g4** Right-click and click on `Generate ANTLR RECOGNIZER` or `CTRL + SHIFT + G`.

 * Right-click and click on `Test Rule Program`


## Pull Request Process

*TODO*

## Styleguides

### Java Styleguide

All Java must adhere to [Twitter's Java Style Guide](https://github.com/twitter/commons/blob/master/src/java/com/twitter/common/styleguide.md) except for :
 * Indent size is 4 spaces, and continuation indent is 8
 * Wildcard imports are tolerated

Also, make sure to :
 * Name tests as following
 	 * `shouldXXXWhenXXX` instead of `someMethodTest`

### ANTLR Styleguide

*TODO*