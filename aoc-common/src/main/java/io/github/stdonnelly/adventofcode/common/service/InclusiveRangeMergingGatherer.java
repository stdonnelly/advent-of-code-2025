package io.github.stdonnelly.adventofcode.common.service;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/// Merge adjacent or overlapping [InclusiveRange]s in a sorted [java.util.stream.Stream]
///
/// Two ranges will be merged if the `start` of the second one is before or equal to the `end` of
/// the first one + 1.
/// For example, all of the the following pairs will be merged to produce the range `1-4`
///
/// - 1-3 and 2-4
/// - 1-2 and 2-4
/// - 1-2 and 3-4 ([InclusiveRange] operates inclusively on integer values, so this would still be
///   considered adjacent.)
///
/// @since 0.5.1
public class InclusiveRangeMergingGatherer
    implements Gatherer<InclusiveRange, InclusiveRangeMergingGatherer.State, InclusiveRange> {

  class State {
    private InclusiveRange current;

    public State(InclusiveRange current) {
      this.current = current;
    }

    public InclusiveRange get() {
      return current;
    }

    public void put(InclusiveRange current) {
      this.current = current;
    }
  }

  @Override
  public Supplier<State> initializer() {
    // The state variable will just be used to store the last InclusiveRange.
    // Because of this, the initial state can just be null.
    return () -> new State(null);
  }

  @Override
  public Integrator<InclusiveRangeMergingGatherer.State, InclusiveRange, InclusiveRange>
      integrator() {
    return Gatherer.Integrator.ofGreedy(
        (state, element, downstream) -> {
          // Null checks
          Objects.requireNonNull(element, "Unable to accept a null InclusiveRange");
          if (state == null) {
            throw new IllegalStateException("state is null");
          }

          // On the first iteration, just consume the element
          if (state.get() == null) {
            state.put(element);
            return true;
          }

          // Otherwise, try to merge the state
          InclusiveRange merged = state.get().mergeWith(element);

          if (merged == null) {
            // If the merge failed, pop to downstream and replace it with the current element
            downstream.push(state.get());
            state.put(element);
          } else {
            // If the merge succeed, merge with the next element and do not push yet
            state.put(merged);
          }

          return !downstream.isRejecting();
        });
  }

  @Override
  public BiConsumer<State, Downstream<? super InclusiveRange>> finisher() {
    return (state, downstream) -> {
      // Null check
      if (state == null || state.get() == null) {
        return;
      }

      downstream.push(state.get());
    };
  }
}
