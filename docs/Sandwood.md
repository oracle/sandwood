# Sandwood Probabilistic Programming Language
This page describes the Sandwood probabilistic programming language. As a running example we will use this [Hidden Markov Model (HMM)](https://en.wikipedia.org/wiki/Hidden_Markov_model).

```java
package org.sandwood.examples.hmm;
 
model HMM(int[] actionsMeasured, int nActions, int nStates) {
  //Construct a transistion matrix m.
  double[] v = new double[nStates] <~ 0.1;
  double[][] m = dirichlet(v).sample(nStates);
 
  //Construct weighting for which state to start in.
  double[] initialState = new Dirichlet(v).sample();
      
  //Construct weighting for each event in each state.
  double[] w = new double[nActions] <~ 0.1;
  double[][] bias = dirichlet(w).sample(nStates);
 
  //Allocate space to record the sequence of states.
  int sequenceLength = actionsMeasured.length;
  int[] st = new int[sequenceLength];
 
  //Calculate the movements between states.
  st[0] = categorical(initialState).sampleDistribution();
  for (int i: [1..sequenceLength) )
    st[i] = categorical(m[st[i - 1]]).sampleDistribution();
 
  //Emit the events for each state.
  int[] actions = new int[sequenceLength];
  for (int j = 0; j < sequenceLength; j++)
    actions[j] = new Categorical(bias[st[j]]).sample();
    
  //Assert that the events match the eventsMeasured data.
  actions.observe(actionsMeasured);
}
```

## Package Declaration
The model starts with an optional package declaration. This works in the same way as package declarations work in Java defining the location in the name space that the resultant classes from the compilation of the model should be placed in.

## Model Signature
The next element in the model is its signature. This takes the keyword `model`, followed by the name of the model, and then a tuple containing the type and name of any model inputs. Each model requires its own file as like with Java the model must be in a file of the same name. The types of the inputs can be primitive types or arrays of primitive types. Currently the only primitive types supported are `int`, `double`, and `boolean`. The order that the inputs appear is the same order that they will appear in the constructor for the resultant model. There will also be an empty constructor allowing input values to be set later, and a constructor where observed values only have a parameter describing their shape. This final one is used when the model is executed to generate values as if it was regular code. This can be used for inferring values from trained models such as linear regression models, or for generating synthetic data. For this example the compiled model will have the following constructors.

`HMM(int[] actionsMeasured, int nActions, int nStates)`

`HMM()`

`HMM(int actionsMeasuredLength, int nActions, int nStates)`

## Declaration of Variables
All variables in Sandwood are single assignment. The enforcement of single assignment semantics is best effort, and models written that break this may fail in unpredictable ways. Like in Java their declaration takes the form:
`type name = value;` 
or if the value is set later
`type name;`

**Arrays**
Arrays are allocated by a call to `new` again following the same syntax as Java, or by declarations with curly braces for example: `int[] a = {1,2,3,4}`. When allocating arrays the values of the elements in the array are not yet set, and can be set later in the model. Sometimes it is convenient to set all the elements in the array to a given value and we provide the syntactic sugar to facilitate this, `double[] b = new double[20] <~ 2.5`. This will assign the value on the right to every element in the newly declared array.

While Sandwood is single assignment so each element in an array can only be set once, all the elements do not have to be set at the same time.

## Random Variables
Sandwood supports the following random variables:
* Bernoulli
* Beta
* Binomial
* Categorical
* Cauchy
* Dirichlet
* Exponential
* Geometric
* Gamma
* Gaussian
* Half Cauchy (HalfCauchy)
* Inverse Gamma (InverseGamma)
* Multinomial
* Negative Binomial (NegativeBinomial)
* Poisson
* Student-T (StudentT)
* Truncated Gaussian (TruncatedGaussian)
* Uniform

There are two ways to construct these, either via an implicit factory method which takes the camel case variable name for example `inverseGamma`, or by calling `new` taking the variable name in as listed above. Examples of both these forms of construction can be seen in the HMM model. Semantically they are identical and are only included to suit different people's coding style preference. Typically the factory method is used as syntactic sugar when the construction of the random variable is in-lined and new is used when the random variable is placed in a named field. Both the constructor and the factory method take a list of arguments defining the parameters of the random variable.

## Sampling Random Variables
There are two methods available for sampling a random variable:
* The first is called `sample` and takes positive integer arguments. If no argument is provided it generates a single value. If  _n_  arguments are provided it will generate an  _n_  dimensional array of samples with the dimensions matching the passed arguments. 

* The second method is `sampleDistribution` which can be called on random variables that produce a finite set of possible values. When this is called a single value will be provided, **but during inference**  every possible value this returned type could take will be explored complete with the corresponding probability. This is used in the example to explore the possible states.

## For Loops
For loops either take the Java structure:

`for(type identifier = value; guard; modifier) body`

or the structure:

`for(type identifier : range) body`

When using Java syntax the guards can only be inequalities, and the modifier must be a constant step size.

###Ranges
The syntax of a range is `[start value .. end value]` either square or curved brackets can be used at each end of a range, and like in mathematical notation a square bracket signifies the inclusion of the value, and the curved bracket signifies the exclusion of the value. Ranges can also include an optional step size value `[start value .. end value, step]`. Some examples of this are below:

`[0..5]    = 0, 1, 2, 3, 4, 5`

`(0..5]    = 1, 2, 3, 4, 5`

`[0..5)    = 0, 1, 2, 3, 4`

`(0..5)    = 1, 2, 3, 4`

`[0..5, 2] = 0, 2, 4`

We expect to expand the set of things that can be iterated over in later releases of the language.

## Conditionals
Models can include conditionals, and conditional assignment, These both follow the standard Java syntax. When assigning variables in conditionals [definite assignment](https://docs.oracle.com/javase/specs/jls/se9/html/jls-16.html) semantics apply. If an unset variable is assigned in one branch of an if else statement it must be assigned in the other branch too. The only exception to this is arrays where the elements of an array can be assigned in one branch without being assigned in the other.

## Observe
The final piece needed to understand the example HMM model is an explanation of the `v.observe(x)` method. This method asserts that when performing value inference, the value of `v` must be equal to the value of `x`. As we will describe in the section on using models, values can be fixed at runtime, however the ability to assert equality through the observe method is useful as it allows the value of `x` to be used for parameterizing array shapes etc, so fixing all the model dependencies to the inputs from the model signature.

## Reduce
To simplify the compilation and understandability of the model, all values are single assignment. This means that to perform an operation such as summing the values in an array some additional functionality is required. To achieve this the method `reduce` is included. This takes as its arguments an array of values of type X, a unit value of type X, and an associative function taking two values of type X and returning one value of type X. These are used to build a binary tree with a lambda at each node and either an array element or a unit value at each leaf. This will be executed to provide the return value. For example, the array `a` of doubles can be summed as follows:

`double sum = reduce (a, 0, (i, j) -> { return i + j; });`

It is not guaranteed how many times the unit variable will be used in the reduction. For example it might be used once in each thread before the partial results are merged to a final result.
