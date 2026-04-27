package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Vulcano2012basic2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Vulcano2012basic2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample26multinomial148$global;
		boolean[][] guard$sample26put123$global;
		boolean[][] guard$sample26put146$global;
		boolean[] guard$sample26put68$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for guard$sample26put68$global
			{
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var63 = 0;
				cv$max_j$var63 = Math.max(cv$max_j$var63, ((state.noProducts - 0) / 1));
				
				// Allocation of guard$sample26put68$global for single threaded execution
				guard$sample26put68$global = new boolean[cv$max_j$var63];
			}
			
			// Constructor for guard$sample26put123$global
			{
				// Calculate the largest index of t that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_t$var105 = 0;
				
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var116 = 0;
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					cv$max_j$var116 = Math.max(cv$max_j$var116, ((state.noProducts - 0) / 1));
				cv$max_t$var105 = Math.max(cv$max_t$var105, ((state.T - 0) / 1));
				
				// Allocation of guard$sample26put123$global for single threaded execution
				guard$sample26put123$global = new boolean[cv$max_t$var105][cv$max_j$var116];
			}
			
			// Constructor for guard$sample26put146$global
			{
				// Calculate the largest index of t that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_t$var105 = 0;
				
				// Calculate the largest index of j that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_j$var140 = 0;
				for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
					cv$max_j$var140 = Math.max(cv$max_j$var140, ((state.noProducts - 0) / 1));
				cv$max_t$var105 = Math.max(cv$max_t$var105, ((state.T - 0) / 1));
				
				// Allocation of guard$sample26put146$global for single threaded execution
				guard$sample26put146$global = new boolean[cv$max_t$var105][cv$max_j$var140];
			}
			
			// Constructor for guard$sample26multinomial148$global
			{
				// Calculate the largest index of t that is possible and allocate an array to hold
				// the guard for each of these.
				int cv$max_t$var105 = 0;
				cv$max_t$var105 = Math.max(cv$max_t$var105, ((state.T - 0) / 1));
				
				// Allocation of guard$sample26multinomial148$global for single threaded execution
				guard$sample26multinomial148$global = new boolean[cv$max_t$var105];
			}
		}
	}


	public Vulcano2012basic2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample26
	private final void drawValueSample26(int j$var20) {
		state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 41.
		{
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						{
							state.exped[j$var38] = Math.exp(state.ut[j$var38]);
						}
					}
				}
			}
		}
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double 52.
		{
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							{
								// Reduction of array exped
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$sum$4 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									double k$var49 = reduceVar$sum$4;
									
									// Set the right hand term to a value from the array exped
									double l$var50 = state.exped[cv$reduction46Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$sum$4 = (k$var49 + l$var50);
								}
								
								// Write out the new sample value.
								state.sum = reduceVar$sum$4;
							}
						}
					}
				}
			}
		}
		
		// Guards to ensure that expedNorm is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 67.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[] guard$sample26put68 = scratch.guard$sample26put68$global;
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
								// Set the flags to false
								guard$sample26put68[((j$var63 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63))
								// Set the flags to false
								guard$sample26put68[((j$var63 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample26put68[((j$var63 - 0) / 1)] = true;
									{
										state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample26put68[((j$var63 - 0) / 1)] = true;
									{
										state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 121.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample26put123 = scratch.guard$sample26put123$global;
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
											// Set the flags to false
											guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
											// Set the flags to false
											guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
												{
													state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
												{
													state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Guards to ensure that weekly_rates is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 26 and consumer double[] 143.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[][] guard$sample26put146 = scratch.guard$sample26put146$global;
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
												for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
													// Set the flags to false
													guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
											if((j$var116 == j$var140)) {
												for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
													// Set the flags to false
													guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
												for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
													// Set the flags to false
													guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
											if((j$var116 == j$var140)) {
												for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
													// Set the flags to false
													guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
												for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$10 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k$var128 = reduceVar$denom$10;
																
																// Set the right hand term to a value from the array weekly_ut
																double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$10 = (k$var128 + l$var129);
															}
															state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$10);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
							for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
											if((j$var116 == j$var140)) {
												for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$11 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k$var128 = reduceVar$denom$11;
																
																// Set the right hand term to a value from the array weekly_ut
																double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$11 = (k$var128 + l$var129);
															}
															state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$11);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
												for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$12 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k$var128 = reduceVar$denom$12;
																
																// Set the right hand term to a value from the array weekly_ut
																double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$12 = (k$var128 + l$var129);
															}
															state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$12);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
											if((j$var116 == j$var140)) {
												for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$13 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k$var128 = reduceVar$denom$13;
																
																// Set the right hand term to a value from the array weekly_ut
																double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$13 = (k$var128 + l$var129);
															}
															state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$13);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Gaussian 25. Inference was performed using Metropolis-Hastings.
	private final void inferSample26(int j$var20) {
		if(true) {
			state.constrainedFlag$sample26[((j$var20 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.ut[j$var20];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			
			// Ensure the variance is at least 0.01
			if((cv$var < 0.01))
				cv$var = 0.01;
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample26[((j$var20 - 1) / 1)] || (cv$valuePos == 0))) {
					// Initialize the summed probabilities to 0.
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					
					// Initialize a counter to track the reached distributions.
					double cv$reachedDistributionSourceRV = 0.0;
					
					// Initialize a log space accumulator to take the product of all the distribution
					// probabilities.
					double cv$accumulatedDistributionProbabilities = 0.0;
					
					// The value currently being tested
					double cv$currentValue;
					if((cv$valuePos == 0))
						// Set the current value to the current state of the tree.
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						
						// Update Sample and intermediate values
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var26 = cv$proposedValue;
						
						// Guards to ensure that ut is only updated when there is a valid path.
						{
							{
								{
									state.ut[j$var20] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 26 and consumer double[] 41.
						{
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										{
											state.exped[j$var38] = Math.exp(state.ut[j$var38]);
										}
									}
								}
							}
						}
						
						// Guards to ensure that sum is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 26 and consumer double 52.
						{
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$0 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k$var49 = reduceVar$sum$0;
													
													// Set the right hand term to a value from the array exped
													double l$var50 = state.exped[cv$reduction46Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$0 = (k$var49 + l$var50);
												}
												
												// Write out the new sample value.
												state.sum = reduceVar$sum$0;
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that expedNorm is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 26 and consumer double[] 67.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[] guard$sample26put68 = scratch.guard$sample26put68$global;
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
												// Set the flags to false
												guard$sample26put68[((j$var63 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63))
												// Set the flags to false
												guard$sample26put68[((j$var63 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample26put68[((j$var63 - 0) / 1)] = true;
													{
														state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample26put68[((j$var63 - 0) / 1)] = true;
													{
														state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that weekly_ut is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 26 and consumer double[] 121.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample26put123 = scratch.guard$sample26put123$global;
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
															// Set the flags to false
															guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
															// Set the flags to false
															guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
																// The body will execute, so should not be executed again
																guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
																{
																	state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
																// The body will execute, so should not be executed again
																guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
																{
																	state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that weekly_rates is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 26 and consumer double[] 143.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample26put146 = scratch.guard$sample26put146$global;
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
																	// Set the flags to false
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																	// Set the flags to false
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
																	// Set the flags to false
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																	// Set the flags to false
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$0 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$0;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$0 = (k$var128 + l$var129);
																			}
																			state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$0);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$1 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$1;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$1 = (k$var128 + l$var129);
																			}
																			state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$1);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$2 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$2;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$2 = (k$var128 + l$var129);
																			}
																			state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$2);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		// The body will execute, so should not be executed again
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$3 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$3;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$3 = (k$var128 + l$var129);
																			}
																			state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$3);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 2.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(2.0))) - (0.5 * Math.log(2.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 145.
						{
							// Looking for a path between Sample 26 and consumer Multinomial 145.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[] guard$sample26multinomial148 = scratch.guard$sample26multinomial148$global;
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts)))
																	// Set the flags to false
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																		// Set the flags to false
																		guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts)))
																	// Set the flags to false
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																		// Set the flags to false
																		guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var39$24_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$k$24_3 = Math.exp(traceTempVariable$var39$24_1);
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												if((0 < state.noProducts)) {
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$1 = 0.0;
													
													// Reduce for every value except a masked value which will be skipped.
													for(int cv$reduction707Index = 0; cv$reduction707Index < j$var38; cv$reduction707Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var49 = reduceVar$sum$1;
														
														// Set the right hand term to a value from the array exped
														double l$var50 = state.exped[cv$reduction707Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$1 = (k$var49 + l$var50);
													}
													for(int cv$reduction707Index = (j$var38 + 1); cv$reduction707Index < state.noProducts; cv$reduction707Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var49 = reduceVar$sum$1;
														
														// Set the right hand term to a value from the array exped
														double l$var50 = state.exped[cv$reduction707Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$1 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$1;
													
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$1 = (traceTempVariable$k$24_3 + cv$reduced46);
													double traceTempVariable$sum$24_4 = reduceVar$sum$1;
													for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
														double traceTempVariable$sum$24_6 = traceTempVariable$sum$24_4;
														double traceTempVariable$var117$24_7 = (state.exped[j$var63] / (state.r * traceTempVariable$sum$24_6));
														for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
															if((j$var63 == j$var116)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																	double traceTempVariable$k$24_10 = (traceTempVariable$var117$24_7 * state.Avail[t$var105][j$var116]);
																	if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																		if((0 < state.noProducts)) {
																			// Reduction of array weekly_ut
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$denom$4 = 0.0;
																			
																			// Reduce for every value except a masked value which will be skipped.
																			for(int cv$reduction741Index = 0; cv$reduction741Index < j$var116; cv$reduction741Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$4;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction741Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$4 = (k$var128 + l$var129);
																			}
																			for(int cv$reduction741Index = (j$var116 + 1); cv$reduction741Index < state.noProducts; cv$reduction741Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double k$var128 = reduceVar$denom$4;
																				
																				// Set the right hand term to a value from the array weekly_ut
																				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction741Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$denom$4 = (k$var128 + l$var129);
																			}
																			double cv$reduced128 = reduceVar$denom$4;
																			
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$4 = (traceTempVariable$k$24_10 + cv$reduced128);
																			double traceTempVariable$denom$24_11 = reduceVar$denom$4;
																			if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																				
																				// Processing sample task 149 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											{
																												// Constructing a random variable input for use later.
																												int var144 = state.sales_sum[t$var105];
																												
																												// Record the probability of sample task 149 generating output with current configuration.
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)));
																												}
																												
																												// Recorded the probability of reaching sample task 149 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																							
																							// A check to ensure rounding of floating point values can never result in a negative
																							// value.
																							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																							
																							// Multiply (log space add) in the probability of the sample task to the overall probability
																							// for this configuration of the source random variable.
																							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																								else
																									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var39$25_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$k$25_3 = Math.exp(traceTempVariable$var39$25_1);
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												if((0 < state.noProducts)) {
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$2 = 0.0;
													
													// Reduce for every value except a masked value which will be skipped.
													for(int cv$reduction769Index = 0; cv$reduction769Index < j$var38; cv$reduction769Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var49 = reduceVar$sum$2;
														
														// Set the right hand term to a value from the array exped
														double l$var50 = state.exped[cv$reduction769Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$2 = (k$var49 + l$var50);
													}
													for(int cv$reduction769Index = (j$var38 + 1); cv$reduction769Index < state.noProducts; cv$reduction769Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var49 = reduceVar$sum$2;
														
														// Set the right hand term to a value from the array exped
														double l$var50 = state.exped[cv$reduction769Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$2 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$2;
													
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$2 = (traceTempVariable$k$25_3 + cv$reduced46);
													double traceTempVariable$sum$25_4 = reduceVar$sum$2;
													for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
														double traceTempVariable$sum$25_6 = traceTempVariable$sum$25_4;
														double traceTempVariable$var117$25_7 = (state.exped[j$var63] / (state.r * traceTempVariable$sum$25_6));
														for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
															if((j$var63 == j$var116)) {
																for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																	double traceTempVariable$var141$25_10 = (traceTempVariable$var117$25_7 * state.Avail[t$var105][j$var116]);
																	for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																		if((j$var116 == j$var140)) {
																			if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																				// The body will execute, so should not be executed again
																				guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																				
																				// Processing sample task 149 of consumer random variable null.
																				{
																					{
																						// Flag recording if this sample task of the consuming random variable is constrained.
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							// Mark that the sample has observed constrained data.
																							state.constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																							
																							// Set an accumulator to sum the probabilities for each possible configuration of
																							// inputs.
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							
																							// Set an accumulator to record the consumer distributions not seen. Initially set
																							// to 1 as seen values will be deducted from this value.
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											{
																												// Constructing a random variable input for use later.
																												int var144 = state.sales_sum[t$var105];
																												
																												// Record the probability of sample task 149 generating output with current configuration.
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)));
																												}
																												
																												// Recorded the probability of reaching sample task 149 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																							
																							// A check to ensure rounding of floating point values can never result in a negative
																							// value.
																							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																							
																							// Multiply (log space add) in the probability of the sample task to the overall probability
																							// for this configuration of the source random variable.
																							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																								else
																									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var39$26_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$var64$26_3 = Math.exp(traceTempVariable$var39$26_1);
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													double traceTempVariable$var117$26_5 = (traceTempVariable$var64$26_3 / (state.r * state.sum));
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																double traceTempVariable$k$26_8 = (traceTempVariable$var117$26_5 * state.Avail[t$var105][j$var116]);
																if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																	if((0 < state.noProducts)) {
																		// Reduction of array weekly_ut
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$denom$5 = 0.0;
																		
																		// Reduce for every value except a masked value which will be skipped.
																		for(int cv$reduction825Index = 0; cv$reduction825Index < j$var116; cv$reduction825Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var128 = reduceVar$denom$5;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction825Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$5 = (k$var128 + l$var129);
																		}
																		for(int cv$reduction825Index = (j$var116 + 1); cv$reduction825Index < state.noProducts; cv$reduction825Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var128 = reduceVar$denom$5;
																			
																			// Set the right hand term to a value from the array weekly_ut
																			double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction825Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$denom$5 = (k$var128 + l$var129);
																		}
																		double cv$reduced128 = reduceVar$denom$5;
																		
																		// Copy the result of the reduction into the variable returned by the reduction.
																		reduceVar$denom$5 = (traceTempVariable$k$26_8 + cv$reduced128);
																		double traceTempVariable$denom$26_9 = reduceVar$denom$5;
																		if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																			
																			// Processing sample task 149 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						state.constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																						
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											// Constructing a random variable input for use later.
																											int var144 = state.sales_sum[t$var105];
																											
																											// Record the probability of sample task 149 generating output with current configuration.
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)));
																											}
																											
																											// Recorded the probability of reaching sample task 149 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
																								}
																							}
																						}
																						
																						// A check to ensure rounding of floating point values can never result in a negative
																						// value.
																						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																						
																						// Multiply (log space add) in the probability of the sample task to the overall probability
																						// for this configuration of the source random variable.
																						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																							else
																								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var39$27_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$var64$27_3 = Math.exp(traceTempVariable$var39$27_1);
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													double traceTempVariable$var117$27_5 = (traceTempVariable$var64$27_3 / (state.r * state.sum));
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																double traceTempVariable$var141$27_8 = (traceTempVariable$var117$27_5 * state.Avail[t$var105][j$var116]);
																for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																	if((j$var116 == j$var140)) {
																		if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																			
																			// Processing sample task 149 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						state.constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																						
																						// Set an accumulator to sum the probabilities for each possible configuration of
																						// inputs.
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						
																						// Set an accumulator to record the consumer distributions not seen. Initially set
																						// to 1 as seen values will be deducted from this value.
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											// Constructing a random variable input for use later.
																											int var144 = state.sales_sum[t$var105];
																											
																											// Record the probability of sample task 149 generating output with current configuration.
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(state.Sales[t$var105], state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144)));
																											}
																											
																											// Recorded the probability of reaching sample task 149 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
																								}
																							}
																						}
																						
																						// A check to ensure rounding of floating point values can never result in a negative
																						// value.
																						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																						
																						// Multiply (log space add) in the probability of the sample task to the overall probability
																						// for this configuration of the source random variable.
																						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																							else
																								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					
					// Save the probability of the original value.
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// Save the probability of the proposed value.
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the changes.
							// 
							// Set the sample value
							// Write out the value of the sample to a temporary variable prior to updating the
							// intermediate variables.
							double var26 = cv$originalValue;
							
							// Guards to ensure that ut is only updated when there is a valid path.
							{
								{
									{
										state.ut[j$var20] = var26;
									}
								}
							}
							
							// Guards to ensure that exped is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 26 and consumer double[] 41.
							{
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											{
												state.exped[j$var38] = Math.exp(state.ut[j$var38]);
											}
										}
									}
								}
							}
							
							// Guards to ensure that sum is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 26 and consumer double 52.
							{
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$3 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k$var49 = reduceVar$sum$3;
														
														// Set the right hand term to a value from the array exped
														double l$var50 = state.exped[cv$reduction46Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$3 = (k$var49 + l$var50);
													}
													
													// Write out the new sample value.
													state.sum = reduceVar$sum$3;
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that expedNorm is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 26 and consumer double[] 67.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[] guard$sample26put68 = scratch.guard$sample26put68$global;
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
													// Set the flags to false
													guard$sample26put68[((j$var63 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63))
													// Set the flags to false
													guard$sample26put68[((j$var63 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put68[((j$var63 - 0) / 1)] = true;
														{
															state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample26put68[((j$var63 - 0) / 1)] = true;
														{
															state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that weekly_ut is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 26 and consumer double[] 121.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample26put123 = scratch.guard$sample26put123$global;
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																// Set the flags to false
																guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																// Set the flags to false
																guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
																	{
																		state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
																	{
																		state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that weekly_rates is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 26 and consumer double[] 143.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample26put146 = scratch.guard$sample26put146$global;
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																	for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
																		// Set the flags to false
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																		// Set the flags to false
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																	for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
																		// Set the flags to false
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
																		// Set the flags to false
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																	for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$6 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var128 = reduceVar$denom$6;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$6 = (k$var128 + l$var129);
																				}
																				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
												for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$7 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var128 = reduceVar$denom$7;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$7 = (k$var128 + l$var129);
																				}
																				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$7);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < state.noProducts))) {
																	for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$8 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var128 = reduceVar$denom$8;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$8 = (k$var128 + l$var129);
																				}
																				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$8);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				// Reduction of array weekly_ut
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$denom$9 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double k$var128 = reduceVar$denom$9;
																					
																					// Set the right hand term to a value from the array weekly_ut
																					double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$denom$9 = (k$var128 + l$var129);
																				}
																				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$9);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample149 using sampled
	// values.
	private final void logProbabilityValue$sample149() {
		// Determine if we need to calculate the values for sample task 149 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample149) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int[] cv$sampleValue = state.Sales[t$var105];
						{
							{
								int var144 = state.sales_sum[t$var105];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, var144));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$weekly_sales = cv$accumulator;
			
			// Update the variable probability
			state.logProbability$Sales = (state.logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample149 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$weekly_sales;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$Sales = (state.logProbability$Sales + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.ut[j$var20];
						{
							{
								double var23 = 0.0;
								double var24 = 2.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var24)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var23) / Math.sqrt(var24))) - (0.5 * Math.log(var24))):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					state.logProbability$sample26[((j$var20 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that expedNorm is only updated once for this probability.
				boolean cv$guard$expedNorm = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 26 and consumer double[] 41.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									state.logProbability$exped = (state.logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 26 and consumer double 52.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										state.logProbability$sum = (state.logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 26 and consumer double[] 67.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
									for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$expedNorm) {
											// Set the guard so the update is only applied once.
											cv$guard$expedNorm = true;
											
											// Update the variable probability
											state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
									if((j$var38 == j$var63)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$expedNorm) {
											// Set the guard so the update is only applied once.
											cv$guard$expedNorm = true;
											
											// Update the variable probability
											state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample26 = state.fixedFlag$sample26;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				double cv$sampleValue = state.logProbability$sample26[((j$var20 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that expedNorm is only updated once for this probability.
				boolean cv$guard$expedNorm = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 26 and consumer double[] 41.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 26 and consumer double 52.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 26 and consumer double[] 67.
				{
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < state.noProducts))) {
									for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$expedNorm) {
											// Set the guard so the update is only applied once.
											cv$guard$expedNorm = true;
											
											// Update the variable probability
											state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					{
						for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
									if((j$var38 == j$var63)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$expedNorm) {
											// Set the guard so the update is only applied once.
											cv$guard$expedNorm = true;
											
											// Update the variable probability
											state.logProbability$expedNorm = (state.logProbability$expedNorm + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample82 using sampled
	// values.
	private final void logProbabilityValue$sample82() {
		// Determine if we need to calculate the values for sample task 82 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample82) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.sales_sum[t$var78];
						{
							{
								double var79 = 0.5;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var79));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					state.logProbability$sample82[((t$var78 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 82 and consumer int[][] 147.
				{
					{
						for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
							if((t$var78 == t$var105)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$Sales) {
									// Set the guard so the update is only applied once.
									cv$guard$Sales = true;
									
									// Update the variable probability
									state.logProbability$Sales = (state.logProbability$Sales + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$sales_sum = (state.logProbability$sales_sum + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample82 = true;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
				double cv$sampleValue = state.logProbability$sample82[((t$var78 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 82 and consumer int[][] 147.
				{
					{
						for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
							if((t$var78 == t$var105)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$Sales) {
									// Set the guard so the update is only applied once.
									cv$guard$Sales = true;
									
									// Update the variable probability
									state.logProbability$Sales = (state.logProbability$Sales + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$sales_sum = (state.logProbability$sales_sum + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.fixedFlag$sample26)
				state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
			if(!state.fixedFlag$sample26)
				state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$5 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$5;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!state.fixedFlag$sample26)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$5 = (k$var49 + l$var50);
		}
		if(!state.fixedFlag$sample26)
			state.sum = reduceVar$sum$5;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			if(!state.fixedFlag$sample26)
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		}
		for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
			if(!state.fixedFlag$sample82)
				state.sales_sum[t$var78] = DistributionSampling.samplePoisson(state.RNG$, 0.5);
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
				if(!state.fixedFlag$sample26)
					state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			}
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$14;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!state.fixedFlag$sample26)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$14 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
				if(!state.fixedFlag$sample26)
					state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$14);
			}
			int[] weekly_sales = state.Sales[t$var105];
			DistributionSampling.sampleMultinomial(state.RNG$, state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, state.sales_sum[t$var105], weekly_sales);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.fixedFlag$sample26)
				state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$9 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$9;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$9 = (k$var49 + l$var50);
		}
		state.sum = reduceVar$sum$9;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$18 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$18;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$denom$18 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$18);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.fixedFlag$sample26)
				state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$6 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$6;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$6 = (k$var49 + l$var50);
		}
		state.sum = reduceVar$sum$6;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1) {
			if(!state.fixedFlag$sample82)
				state.sales_sum[t$var78] = DistributionSampling.samplePoisson(state.RNG$, 0.5);
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$15;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$denom$15 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$15);
			int[] weekly_sales = state.Sales[t$var105];
			DistributionSampling.sampleMultinomial(state.RNG$, state.weekly_rates[((t$var105 - 0) / 1)], state.noProducts, state.sales_sum[t$var105], weekly_sales);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.fixedFlag$sample26)
				state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1) {
			if(!state.fixedFlag$sample26)
				state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$7 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$7;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!state.fixedFlag$sample26)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$7 = (k$var49 + l$var50);
		}
		if(!state.fixedFlag$sample26)
			state.sum = reduceVar$sum$7;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1) {
			if(!state.fixedFlag$sample26)
				state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		}
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1) {
				if(!state.fixedFlag$sample26)
					state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			}
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$16 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$16;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!state.fixedFlag$sample26)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$16 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1) {
				if(!state.fixedFlag$sample26)
					state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$16);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.fixedFlag$sample26)
				state.ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$8 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$8;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$8 = (k$var49 + l$var50);
		}
		state.sum = reduceVar$sum$8;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$17 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$17;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$denom$17 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$17);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
				if(!state.fixedFlag$sample26)
					inferSample26(j$var20);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int j$var20 = (state.noProducts - ((((state.noProducts - 1) - 1) % 1) + 1)); j$var20 >= ((1 - 1) + 1); j$var20 -= 1) {
				if(!state.fixedFlag$sample26)
					inferSample26(j$var20);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1) {
			if(!state.constrainedFlag$sample26[((j$var20 - 1) / 1)])
				drawValueSample26(j$var20);
		}
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$expedNorm = 0.0;
		if(!state.fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < state.noProducts; j$var20 += 1)
				state.logProbability$sample26[((j$var20 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$sales_sum = 0.0;
		state.logProbability$Sales = 0.0;
		if(!state.fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < state.T; t$var78 += 1)
				state.logProbability$sample82[((t$var78 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample149)
			state.logProbability$weekly_sales = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < state.constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			state.constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Reset any fixed flags on observed values
		state.fixedFlag$sample82 = false;
		
		// Propagating values back from observations into the models intermediate variables.
		{
			{
				// Deep copy between arrays
				int[][] cv$source1 = state.ObsSales;
				int[][] cv$target1 = state.Sales;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					int[] cv$source2 = cv$source1[cv$index1];
					int[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int t$var105 = (state.T - ((((state.T - 1) - 0) % 1) + 1)); t$var105 >= ((0 - 1) + 1); t$var105 -= 1) {
				int[] weekly_sales;
				weekly_sales = state.Sales[t$var105];
				int cv$multinomialSum148 = 0;
				
				// Sum the number of samples in the multinomial output.
				for(int cv$multinomialIndex148 = 0; cv$multinomialIndex148 < weekly_sales.length; cv$multinomialIndex148 += 1)
					cv$multinomialSum148 = (weekly_sales[cv$multinomialIndex148] + cv$multinomialSum148);
				state.sales_sum[t$var105] = cv$multinomialSum148;
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		for(int j$var38 = 0; j$var38 < state.noProducts; j$var38 += 1)
			state.exped[j$var38] = Math.exp(state.ut[j$var38]);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$10 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction46Index = 0; cv$reduction46Index < state.noProducts; cv$reduction46Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double k$var49 = reduceVar$sum$10;
			
			// Set the right hand term to a value from the array exped
			double l$var50 = state.exped[cv$reduction46Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$10 = (k$var49 + l$var50);
		}
		state.sum = reduceVar$sum$10;
		for(int j$var63 = 0; j$var63 < state.noProducts; j$var63 += 1)
			state.expedNorm[j$var63] = (state.exped[j$var63] / (state.r * state.sum));
		for(int t$var105 = 0; t$var105 < state.T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < state.noProducts; j$var116 += 1)
				state.weekly_ut[((t$var105 - 0) / 1)][j$var116] = (state.expedNorm[j$var116] * state.Avail[t$var105][j$var116]);
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$19 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction128Index = 0; cv$reduction128Index < state.noProducts; cv$reduction128Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var128 = reduceVar$denom$19;
				
				// Set the right hand term to a value from the array weekly_ut
				double l$var129 = state.weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$denom$19 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < state.noProducts; j$var140 += 1)
				state.weekly_rates[((t$var105 - 0) / 1)][j$var140] = (state.weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$19);
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