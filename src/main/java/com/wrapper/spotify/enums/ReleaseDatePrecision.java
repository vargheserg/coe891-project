package com.wrapper.spotify.enums;

/**
 * An enumeration of all possible release date precisions.
 */
public enum ReleaseDatePrecision {

  DAY("day"),
  MONTH("month"),
  YEAR("year");

  public final String precision;

  ReleaseDatePrecision(final String precision) {
    this.precision = precision;
  }

  /**
   * Get the release date precision as a string.
   *
   * @return The release date precision as a string.
   */
  public String getPrecision() {
    return precision;
  }

}
