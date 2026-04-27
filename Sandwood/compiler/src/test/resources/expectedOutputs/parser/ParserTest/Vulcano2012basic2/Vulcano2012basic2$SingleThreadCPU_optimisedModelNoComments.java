package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Vulcano2012basic2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Vulcano2012basic2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
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


	public Vulcano2012basic2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample26(int j$var20) {
		state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		state.exped[j$var20] = Math.exp(state.ut[j$var20]);
		double reduceVar$sum$4 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$4 = (reduceVar$sum$4 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$4;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			scratch.guard$sample26put68$global[j$var63] = false;
		scratch.guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			if(!scratch.guard$sample26put68$global[j$var63]) {
				scratch.guard$sample26put68$global[j$var63] = true;
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$4));
			}
		}
		if(!scratch.guard$sample26put68$global[j$var20]) {
			scratch.guard$sample26put68$global[j$var20] = true;
			state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$4));
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
					double reduceVar$denom$10 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$10 = (reduceVar$denom$10 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$10);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
					scratch.guard$sample26put146$global[t$var105][j$var63] = true;
					double reduceVar$denom$11 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$11 = (reduceVar$denom$11 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$11);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
					scratch.guard$sample26put146$global[t$var105][j$var140] = true;
					double reduceVar$denom$12 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$12 = (reduceVar$denom$12 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$12);
				}
			}
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
				scratch.guard$sample26put146$global[t$var105][j$var20] = true;
				double reduceVar$denom$13 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
					reduceVar$denom$13 = (reduceVar$denom$13 + state.weekly_ut[t$var105][cv$reduction128Index]);
				state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$13);
			}
		}
	}

	private final void inferSample26(int j$var20) {
		state.constrainedFlag$sample26[(j$var20 - 1)] = false;
		double cv$originalValue = state.ut[j$var20];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
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
			double reduceVar$sum$0 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$0 = (reduceVar$sum$0 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$0;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
				scratch.guard$sample26put68$global[j$var63] = false;
			scratch.guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				if(!scratch.guard$sample26put68$global[j$var63]) {
					scratch.guard$sample26put68$global[j$var63] = true;
					state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$0));
				}
			}
			if(!scratch.guard$sample26put68$global[j$var20]) {
				scratch.guard$sample26put68$global[j$var20] = true;
				state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$0));
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
						double reduceVar$denom$0 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$0 = (reduceVar$denom$0 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$0);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
						scratch.guard$sample26put146$global[t$var105][j$var63] = true;
						double reduceVar$denom$1 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$1 = (reduceVar$denom$1 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$1);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
						scratch.guard$sample26put146$global[t$var105][j$var140] = true;
						double reduceVar$denom$2 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$2 = (reduceVar$denom$2 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$2);
					}
				}
			}
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
					scratch.guard$sample26put146$global[t$var105][j$var20] = true;
					double reduceVar$denom$3 = 0.0;
					for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
						reduceVar$denom$3 = (reduceVar$denom$3 + state.weekly_ut[t$var105][cv$reduction128Index]);
					state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$3);
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
				double reduceVar$sum$3 = 0.0;
				for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
					reduceVar$sum$3 = (reduceVar$sum$3 + state.exped[cv$reduction46Index]);
				state.sum = reduceVar$sum$3;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
					scratch.guard$sample26put68$global[j$var63] = false;
				scratch.guard$sample26put68$global[j$var20] = false;
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					if(!scratch.guard$sample26put68$global[j$var63]) {
						scratch.guard$sample26put68$global[j$var63] = true;
						state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$3));
					}
				}
				if(!scratch.guard$sample26put68$global[j$var20]) {
					scratch.guard$sample26put68$global[j$var20] = true;
					state.expedNorm[j$var20] = (state.exped[j$var20] / (state.r * reduceVar$sum$3));
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
							double reduceVar$denom$6 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$6 = (reduceVar$denom$6 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$6);
						}
					}
				}
				for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
					for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
						if(!scratch.guard$sample26put146$global[t$var105][j$var63]) {
							scratch.guard$sample26put146$global[t$var105][j$var63] = true;
							double reduceVar$denom$7 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$7 = (reduceVar$denom$7 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var63] = (state.weekly_ut[t$var105][j$var63] / reduceVar$denom$7);
						}
					}
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
						if(!scratch.guard$sample26put146$global[t$var105][j$var140]) {
							scratch.guard$sample26put146$global[t$var105][j$var140] = true;
							double reduceVar$denom$8 = 0.0;
							for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
								reduceVar$denom$8 = (reduceVar$denom$8 + state.weekly_ut[t$var105][cv$reduction128Index]);
							state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$8);
						}
					}
				}
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
					if(!scratch.guard$sample26put146$global[t$var105][j$var20]) {
						scratch.guard$sample26put146$global[t$var105][j$var20] = true;
						double reduceVar$denom$9 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
							reduceVar$denom$9 = (reduceVar$denom$9 + state.weekly_ut[t$var105][cv$reduction128Index]);
						state.weekly_rates[t$var105][j$var20] = (state.weekly_ut[t$var105][j$var20] / reduceVar$denom$9);
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
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
				state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			double reduceVar$sum$5 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$5 = (reduceVar$sum$5 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$5;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$5));
		}
		if(!state.fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1)
				state.sales_sum[t$var78] = DistributionSampling.samplePoisson(state.RNG$, 0.5);
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			if(!state.fixedFlag$sample26) {
				for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
					state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
				double reduceVar$denom$14 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
					reduceVar$denom$14 = (reduceVar$denom$14 + state.weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$14);
			}
			DistributionSampling.sampleMultinomial(state.RNG$, state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105], state.Sales[t$var105]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		double reduceVar$sum$9 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$9;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$9));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			double reduceVar$denom$18 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
				reduceVar$denom$18 = (reduceVar$denom$18 + state.weekly_ut[t$var105][cv$reduction128Index]);
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$18);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		double reduceVar$sum$6 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$6 = (reduceVar$sum$6 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$6;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$6));
		if(!state.fixedFlag$sample82) {
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1)
				state.sales_sum[t$var78] = DistributionSampling.samplePoisson(state.RNG$, 0.5);
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			double reduceVar$denom$15 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
				reduceVar$denom$15 = (reduceVar$denom$15 + state.weekly_ut[t$var105][cv$reduction128Index]);
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$15);
			DistributionSampling.sampleMultinomial(state.RNG$, state.weekly_rates[t$var105], state.noProducts, state.sales_sum[t$var105], state.Sales[t$var105]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
			for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
				state.exped[j$var38] = Math.exp(state.ut[j$var38]);
			double reduceVar$sum$7 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
				reduceVar$sum$7 = (reduceVar$sum$7 + state.exped[cv$reduction46Index]);
			state.sum = reduceVar$sum$7;
			for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$7));
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
					state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
				double reduceVar$denom$16 = 0.0;
				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
					reduceVar$denom$16 = (reduceVar$denom$16 + state.weekly_ut[t$var105][cv$reduction128Index]);
				for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
					state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$16);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.ut[j$var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$8 = (reduceVar$sum$8 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$8;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$8));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			double reduceVar$denom$17 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
				reduceVar$denom$17 = (reduceVar$denom$17 + state.weekly_ut[t$var105][cv$reduction128Index]);
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$17);
		}
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
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		double reduceVar$sum$10 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1)
			reduceVar$sum$10 = (reduceVar$sum$10 + state.exped[cv$reduction46Index]);
		state.sum = reduceVar$sum$10;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * reduceVar$sum$10));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[t$var105][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			double reduceVar$denom$19 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1)
				reduceVar$denom$19 = (reduceVar$denom$19 + state.weekly_ut[t$var105][cv$reduction128Index]);
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[t$var105][j$var140] = (state.weekly_ut[t$var105][j$var140] / reduceVar$denom$19);
		}
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