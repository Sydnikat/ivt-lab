package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPr;
  private TorpedoStore mockSc;

  @BeforeEach
  public void init(){
    mockPr = mock(TorpedoStore.class);
    mockSc = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPr, mockSc);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    //when(mockPr.getTorpedoCount()).thenReturn(10);
    when(mockPr.fire(1)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).fire(1);
    verify(mockPr, times(1)).isEmpty();
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    //when(mockPr.getTorpedoCount()).thenReturn(10);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
