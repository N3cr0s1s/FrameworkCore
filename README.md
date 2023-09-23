<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="media/fwcore_logo.png" alt="Logo" width="100" height="100">
  </a>

<h1 align="center">Framework Core</h1>

  <p align="center">
    A framework core, to kickstart your Java applications!
    <br />
    <p>Created by: <strong>Necrosis</strong></p>
    <br />
    <a href="https://github.com/N3cr0s1s/FrameworkCore/issues">Report Bug</a>
    ·
    <a href="https://github.com/N3cr0s1s/FrameworkCore/issues">Request Feature</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project

This repository created because I need this tools in my projects, 
and I don't like boilerplate code.
<br>
This is a personal tool, but I thought I'd make it public in case someone could use it.

<!-- Getting started -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally. 
To get a local copy up and running follow these simple example steps.

* Maven <strong>(This is not working yet!...)</strong>
  ```xml
  <dependency>
    <groupId>me.necrosis.fwc</groupId>
    <artifactId>fw-core</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
  ```

### Create a framework

To create a new framework, just create a new class, and extend with the 
FrameworkCore.

```java
public class MyFramework extends FrameworkCore {
  //    ...
}
```

> [!IMPORTANT]  
> FrameworkCore has a constructor which is requires one arg, which is FwOption.<br>
> With this object, you can specify FW options.<br>
> I recommend to use this constructor, but there is a default one either.

#### Modules (Dependency injection)

Framework main function is the Dependency Injection (DI).
It's useful because you don't need to handle services life cycles.

Before you do anything else, you need to override some method.<br>
Firstly you need to Override the `configureServices`. This method returns with a new `AbstractModule`.
This module will be used by Dependency Injection (DI).

```java
public class MyFramework extends FrameworkCore {

  @Override
  public AbstractModule configureServices() {
    return new AbstractModule() {
      @Override
      protected void configure() {
          // There you can register your services like this:
        
          // bind(INTERFACE).to(IMPLEMENTATION).in(SCOPE)
          bind(TransientService.class).to(TransientServiceImpl.class).in(Scopes.NO_SCOPE);
          bind(SingletonService.class).to(TransientServiceImpl.class).in(Scopes.SINGLETON);
          
          // You can register services without interface with:
          bind(TestClass.class).in(Scopes.SINGLETON);
      }
    };
  }
   
  // ...
}
```

To get services you can use :

```java
public class MyFramework extends FrameworkCore {
  //...
  public void nothing(){
    TransientService transientService = getInjector().getInstance(TransientService.class);
  }
  //...
}
```

#### OnStart

To start you framework Override the `onStart` method.
<br>
This method will run right after the framework init,
but you can overwrite this with the FwOptions.
Just make sure your `FwOptions#autoStart` is `false`.

> [!WARNING]  
> If you are using `FwOptions#autoStart` with `false`, then don't forget to call `FrameworkCore#handleStart` method!
> This method handle the full Start/Stop cycle and Exception handling.

#### OnEnd

`OnEnd` method will execute after the `OnStart` method.
This method gives you one argument, which is a boolean.
This boolean is true if the start method ended with an exception, otherwise false.

#### OnFwException

`OnFwException` this will catch all exceptions that occurred 
in the `onStart` method or the `onEnd` method.<br>
This will give you 2 args, first is the `Thread`, second is a `FwException`.
To get the real exception, use this: `FwException#getException()`

#### Plugins

You can create plugins for the framework.
To register a plugin use the `FrameworkCore#registerPlugins()` method.
<br>
This method need to return with a list of the plugins.
<br><br>
In FrameworkCore plugins basically just a new `AbstractModule`, which is a DI module.

### Examples

For examples see the test project. (fw-test)

## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
