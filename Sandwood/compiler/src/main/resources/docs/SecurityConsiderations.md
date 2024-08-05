# Security Considerations
Sandwood is a compiler and runtime for probabilistic programming models written in the Sandwood DSL. 

The compiler is a tool that can either act as a stand-alone component, or as a function called from within another program. It checks its input by comparing it against a list of known configuration flags, and by parsing the model descriptions with a fixed grammar that does not accept arbitrary code. 

The compiled models and supporting runtime are expected to appear as part of larger system therefore, we consider the trust boundary to live somewhere outside the model in the larger program. While we provide data loaders, we expect them to be used on trusted or cleaned data. We expect that the larger program will control access to the model interfaces.

## Threat Model
As models are incorporated into other programs, it is expected that Sandwood models inputs are
checked by the wider program; however, there are threats which are specific to
ML systems that can result in model or data leakage.

| Threat | Description | Exposed Assets | Possible Mitigations |
| ------ | ----------- | -------------- | -------------------- |
| Model replication | If an attacker can repeatedly query the model, where they either know or control the features, and they can observe the full prediction (e.g., the complete predicted probability distribution) for each query, then this can provide sufficient information for them to replicate the model.  If the model is considered an important asset, allowing an attacker to copy it could be detrimental. | The model parameters | Only return a small number of predictions (i.e., the top n) or do not provide the probability distribution. This slows down the attack, but does not completely prevent it. Other mitigations such as employing rate limiting or preventing the attacker from controlling or observing the feature inputs are necessary to fully prevent this attack.|
| Training metadata leak | The model file contains information about the training data such as the feature names, number of features, and number of examples. This information is potentially sensitive, as in the case of bigrams or trigrams from text. | Training metadata | Treat the model files as confidential if the data itself is confidential. | 
| Training data leak | If an attacker can repeatedly query the model, it's possible for an attacker to find specific training data points that are part of the training data set. This attack is accomplished by measuring the confidence of the prediction (as training data points usually have a predicted confidence close to 1.0). | Training data | The most important mitigation is to treat model files as confidential if the training data is confidential. Once access to the model has been prevented, the mitigations for model replication apply. This attack is a variant of model replication that usually requires some foreknowledge of the identity of the training corpus. |
