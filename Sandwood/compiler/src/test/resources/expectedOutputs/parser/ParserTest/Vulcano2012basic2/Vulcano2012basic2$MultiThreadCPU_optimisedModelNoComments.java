package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Vulcano2012basic2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Vulcano2012basic2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample26multinomial148$global;
		boolean[][] guard$sample26put123$global;
		boolean[][] guard$sample26put146$global;
		boolean[] guard$sample26put68$global;

		@Override
		public final void allocateScratch() {
			guard$sample26put68$global = new boolean[Math.max(0, state.noProducts)];
			int cv$max_j$var116 = 0;
			if((0 < state.T))
				cv$max_j$var116 = Math.max(0, state.noProducts);
			guard$sample26put123$global = new boolean[Math.max(0, state.T)][cv$max_j$var116];
			int cv$max_j$var140 = 0;
			if((0 < state.T))
				cv$max_j$var140 = Math.max(0, state.noProducts);
			guard$sample26put146$global = new boolean[Math.max(0, state.T)][cv$max_j$var140];
			guard$sample26multinomial148$global = new boolean[Math.max(0, state.T)];
		}
	}


	public Vulcano2012basic2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample26(int j$var20) {
		state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		state.exped[j$var20] = Math.exp(state.ut[j$var20]);
		double reduceVar$sum$15 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$15 = (reduceVar$sum$15 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$15;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			scratch.guard$sample26put68$global[j$var63] = false;
		scratch.guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			if(!scratch.guard$sample26put68$global[j$var63]) {
				scratch.guard$sample26put68$global[j$var63] = true;
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$15));
			}
		}
		if(!scratch.guard$sample26put68$global[j$var20]) {
			scratch.guard$sample26put68$global[j$var20] = true;
			state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$15));
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26put123$global[t$var105][j$var63] = false;
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
			scratch.guard$sample26put123$global[t$var105][j$var20] = false;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put123$global[t$var105][j$var63]) {
					scratch.guard$sample26put123$global[t$var105][j$var63] = true;
					state.weekly_ut[t$var105][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var105][j$var63]);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			if(!scratch.guard$sample26put123$global[t$var105][j$var20]) {
				scratch.guard$sample26put123$global[t$var105][j$var20] = true;
				state.weekly_ut[t$var105][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var105][j$var20]);
			}
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26put146$global[t$var105][j$var63] = false;
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				scratch.guard$sample26put146$global[t$var105][j$var140] = false;
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
			scratch.guard$sample26put146$global[t$var105][j$var20] = false;
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
					scratch.guard$sample26put146$global[t$var105][j$var140] = true;
					double reduceVar$denom$30 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$30 = (reduceVar$denom$30 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$30);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
					scratch.guard$sample26put146$global[t$var105][j$var63] = true;
					double reduceVar$denom$31 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$31 = (reduceVar$denom$31 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$31);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
					scratch.guard$sample26put146$global[t$var105][j$var140] = true;
					double reduceVar$denom$32 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$32 = (reduceVar$denom$32 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$32);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
				scratch.guard$sample26put146$global[t$var105][j$var20] = true;
				double reduceVar$denom$33 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
					reduceVar$denom$33 = (reduceVar$denom$33 + state.weekly_ut[t$var105][cv$reduction128Index]);
				state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$33);
			}
		}
	}

	private final void inferSample26(int j$var20) {
		state.constrainedFlag$sample26[(j$var20 - 1)] = false;
		double cv$originalValue = state.ut[j$var20];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26multinomial148$global[t$var105] = false;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample26[(j$var20 - 1)]) {
			state.ut[j$var20] = cv$proposedValue;
			state.exped[j$var20] = Math.exp(state.ut[j$var20]);
			double reduceVar$sum$11 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$11 = (reduceVar$sum$11 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$11;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
				scratch.guard$sample26put68$global[j$var63] = false;
			scratch.guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				if(!scratch.guard$sample26put68$global[j$var63]) {
					scratch.guard$sample26put68$global[j$var63] = true;
					state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$11));
				}
			}
			if(!scratch.guard$sample26put68$global[j$var20]) {
				scratch.guard$sample26put68$global[j$var20] = true;
				state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$11));
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					scratch.guard$sample26put123$global[t$var105][j$var63] = false;
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26put123$global[t$var105][j$var20] = false;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put123$global[t$var105][j$var63]) {
						scratch.guard$sample26put123$global[t$var105][j$var63] = true;
						state.weekly_ut[t$var105][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var105][j$var63]);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put123$global[t$var105][j$var20]) {
					scratch.guard$sample26put123$global[t$var105][j$var20] = true;
					state.weekly_ut[t$var105][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var105][j$var20]);
				}
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					scratch.guard$sample26put146$global[t$var105][j$var63] = false;
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
					scratch.guard$sample26put146$global[t$var105][j$var140] = false;
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26put146$global[t$var105][j$var20] = false;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
						scratch.guard$sample26put146$global[t$var105][j$var140] = true;
						double reduceVar$denom$20 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$20 = (reduceVar$denom$20 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$20);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
						scratch.guard$sample26put146$global[t$var105][j$var63] = true;
						double reduceVar$denom$21 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$21 = (reduceVar$denom$21 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$21);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
						scratch.guard$sample26put146$global[t$var105][j$var140] = true;
						double reduceVar$denom$22 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$22 = (reduceVar$denom$22 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$22);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
					scratch.guard$sample26put146$global[t$var105][j$var20] = true;
					double reduceVar$denom$23 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$23 = (reduceVar$denom$23 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$23);
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				scratch.guard$sample26multinomial148$global[t$var105] = false;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26multinomial148$global[t$var105]) {
					scratch.guard$sample26multinomial148$global[t$var105] = true;
					state.constrainedFlag$sample26[(j$var20 - 1)] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.ut[j$var20] = cv$originalValue;
				state.exped[j$var20] = Math.exp(state.ut[j$var20]);
				double reduceVar$sum$14 = 0.0;
				for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
					reduceVar$sum$14 = (reduceVar$sum$14 + state.exped[cv$reduction46Index]);
				state.sum = reduceVar$sum$14;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
					scratch.guard$sample26put68$global[j$var63] = false;
				scratch.guard$sample26put68$global[j$var20] = false;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					if(!scratch.guard$sample26put68$global[j$var63]) {
						scratch.guard$sample26put68$global[j$var63] = true;
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$14));
					}
				}
				if(!scratch.guard$sample26put68$global[j$var20]) {
					scratch.guard$sample26put68$global[j$var20] = true;
					state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$14));
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
						scratch.guard$sample26put123$global[t$var105][j$var63] = false;
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					scratch.guard$sample26put123$global[t$var105][j$var20] = false;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
						if(!scratch.guard$sample26put123$global[t$var105][j$var63]) {
							scratch.guard$sample26put123$global[t$var105][j$var63] = true;
							state.weekly_ut[t$var105][j$var63] = (state.expedNorm[j$var63] * state.Avail[t$var105][j$var63]);
						}
					}
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put123$global[t$var105][j$var20]) {
						scratch.guard$sample26put123$global[t$var105][j$var20] = true;
						state.weekly_ut[t$var105][j$var20] = (state.expedNorm[j$var20] * state.Avail[t$var105][j$var20]);
					}
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
						scratch.guard$sample26put146$global[t$var105][j$var63] = false;
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
						scratch.guard$sample26put146$global[t$var105][j$var140] = false;
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					scratch.guard$sample26put146$global[t$var105][j$var20] = false;
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
						if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
							scratch.guard$sample26put146$global[t$var105][j$var140] = true;
							double reduceVar$denom$26 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$26 = (reduceVar$denom$26 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$26);
						}
					}
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
						if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
							scratch.guard$sample26put146$global[t$var105][j$var63] = true;
							double reduceVar$denom$27 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$27 = (reduceVar$denom$27 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$27);
						}
					}
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
						if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
							scratch.guard$sample26put146$global[t$var105][j$var140] = true;
							double reduceVar$denom$28 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$28 = (reduceVar$denom$28 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$28);
						}
					}
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
						scratch.guard$sample26put146$global[t$var105][j$var20] = true;
						double reduceVar$denom$29 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$29 = (reduceVar$denom$29 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$29);
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample149() {
		if(!state.fixedProbFlag$sample149) {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample149[t$var105] = cv$distributionAccumulator;
			}
			state.logProbability$Sales = (state.logProbability$Sales + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample149 = state.fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample149[t$var105]);
			state.logProbability$Sales = (state.logProbability$Sales + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!state.fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[j$var20] / 1.4142135623730951)) - 0.34657359027997264);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample26[(j$var20 - 1)] = cv$distributionAccumulator;
				state.logProbability$exped = (state.logProbability$exped + cv$distributionAccumulator);
				state.logProbability$sum = (state.logProbability$sum + cv$distributionAccumulator);
				state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$distributionAccumulator);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample26 = state.fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				double cv$sampleValue = state.logProbability$sample26[(j$var20 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
				state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
				state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleValue);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!state.fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(state.sales_sum[t$var78], 0.5);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample82[t$var78] = cv$distributionAccumulator;
				state.logProbability$Sales = (state.logProbability$Sales + cv$distributionAccumulator);
			}
			state.logProbability$sales_sum = (state.logProbability$sales_sum + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample82 = true;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
				double cv$sampleValue = state.logProbability$sample82[t$var78];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$Sales = (state.logProbability$Sales + cv$sampleValue);
			}
			state.logProbability$sales_sum = (state.logProbability$sales_sum + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample26) {
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							state.exped[j$var38] = Math.exp(state.ut[j$var38]);
				}
			);
			double reduceVar$sum$16 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$16 = (reduceVar$sum$16 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$16;
			double reduceVar$sum$16$1 = reduceVar$sum$16;
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$16$1));
				}
			);
		}
		if(!state.fixedFlag$sample82)
			parallelFor(state.RNG$, 0, state.T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							state.sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$1, 0.5);
				}
			);

		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						if(!state.fixedFlag$sample26) {
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
											state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
								}
							);
							double reduceVar$denom$34 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$34 = (reduceVar$denom$34 + state.weekly_ut[t$var105][cv$reduction128Index]);
							double reduceVar$denom$34$2 = reduceVar$denom$34;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
											state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$34$2);
								}
							);
						}
						DistributionSampling.sampleMultinomial(RNG$1, state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105], state.Sales[t$var105]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample26)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		double reduceVar$sum$20 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$20 = (reduceVar$sum$20 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$20;
		double reduceVar$sum$20$1 = reduceVar$sum$20;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$20$1));
			}
		);
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$38 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$38 = (reduceVar$denom$38 + state.weekly_ut[t$var105][cv$reduction128Index]);
						double reduceVar$denom$38$2 = reduceVar$denom$38;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$38$2);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample26)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		double reduceVar$sum$17 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$17 = (reduceVar$sum$17 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$17;
		double reduceVar$sum$17$1 = reduceVar$sum$17;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$17$1));
			}
		);
		if(!state.fixedFlag$sample82)
			parallelFor(state.RNG$, 0, state.T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							state.sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$1, 0.5);
				}
			);

		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$35 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$35 = (reduceVar$denom$35 + state.weekly_ut[t$var105][cv$reduction128Index]);
						double reduceVar$denom$35$2 = reduceVar$denom$35;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$35$2);
							}
						);
						DistributionSampling.sampleMultinomial(RNG$1, state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105], state.Sales[t$var105]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample26) {
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							state.exped[j$var38] = Math.exp(state.ut[j$var38]);
				}
			);
			double reduceVar$sum$18 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$18 = (reduceVar$sum$18 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$18;
			double reduceVar$sum$18$1 = reduceVar$sum$18;
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$18$1));
				}
			);
			parallelFor(state.RNG$, 0, state.T, 1,
				(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
							int t$var105 = index$t$var105;
							int threadID$t$var105 = threadID$index$t$var105;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
											state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
								}
							);
							double reduceVar$denom$36 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$36 = (reduceVar$denom$36 + state.weekly_ut[t$var105][cv$reduction128Index]);
							double reduceVar$denom$36$2 = reduceVar$denom$36;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
											state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$36$2);
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample26)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							state.ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		double reduceVar$sum$19 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$19 = (reduceVar$sum$19 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$19;
		double reduceVar$sum$19$1 = reduceVar$sum$19;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$19$1));
			}
		);
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$37 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$37 = (reduceVar$denom$37 + state.weekly_ut[t$var105][cv$reduction128Index]);
						double reduceVar$denom$37$2 = reduceVar$denom$37;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$37$2);
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample26) {
			if(state.system$gibbsForward) {
				for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
					inferSample26(j$var20);
			} else {
				for(int j$var20 = (state.noProducts - 1); j$var20 >= 1; j$var20 -= 1)
					inferSample26(j$var20);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.constrainedFlag$sample26[(j$var20 - 1)])
				drawValueSample26(j$var20);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$expedNorm = 0.0;
		if(!state.fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.logProbability$sample26[(j$var20 - 1)] = Double.NaN;
		}
		state.logProbability$sales_sum = 0.0;
		state.logProbability$Sales = 0.0;
		if(!state.fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1)
				state.logProbability$sample82[t$var78] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample149) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				state.logProbability$sample149[t$var105] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < state.constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			state.constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void propagateObservedValues() {
		state.fixedFlag$sample82 = false;
		int cv$length1 = state.Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.ObsSales[cv$index1];
			int[] cv$target2 = state.Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int t$var105 = (state.T - 1); t$var105 >= 0; t$var105 -= 1) {
			int[] weekly_sales = state.Sales[t$var105];
			int cv$multinomialSum148 = 0;
			for(int cv$multinomialIndex148 = 0; cv$multinomialIndex148 < weekly_sales.length; cv$multinomialIndex148 += 1)
				cv$multinomialSum148 = (weekly_sales[cv$multinomialIndex148] + cv$multinomialSum148);
			state.sales_sum[t$var105] = cv$multinomialSum148;
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			}
		);
		double reduceVar$sum$21 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$21 = (reduceVar$sum$21 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$21;
		double reduceVar$sum$21$1 = reduceVar$sum$21;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$21$1));
			}
		);
		parallelFor(state.RNG$, 0, state.T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$39 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$39 = (reduceVar$denom$39 + state.weekly_ut[t$var105][cv$reduction128Index]);
						double reduceVar$denom$39$2 = reduceVar$denom$39;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$39$2);
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic2(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        sales_sum[t] = poisson(0.5).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}