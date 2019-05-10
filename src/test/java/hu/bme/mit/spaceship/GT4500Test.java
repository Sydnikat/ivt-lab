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
  public void fireTorpedo_Single_Failiure_No_Torpedo(){
    // Arrange
    when(mockPr.isEmpty()).thenReturn(true);
    when(mockSc.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).isEmpty();
    verify(mockSc, times(1)).isEmpty();
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Two_Times_But_Out_Of_Torpedos_At_Second(){
    // Arrange
    when(mockPr.fire(1)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).fire(1);
    verify(mockPr, times(1)).isEmpty();
    assertEquals(true, result);

    when(mockSc.isEmpty()).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(true);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockSc, times(1)).isEmpty();
    verify(mockPr, times(2)).isEmpty();
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Two_Times(){
    // Arrange
    when(mockPr.fire(1)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    when(mockSc.fire(1)).thenReturn(true);
    when(mockSc.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).fire(1);
    verify(mockPr, times(1)).isEmpty();
    assertEquals(true, result);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockSc, times(1)).fire(1);
    verify(mockSc, times(1)).isEmpty();
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Two_Times_But_Secondary_Is_Empty(){
    // Arrange
    when(mockPr.fire(1)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    when(mockSc.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).fire(1);
    verify(mockPr, times(1)).isEmpty();
    assertEquals(true, result);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockSc, times(1)).isEmpty();
    verify(mockPr, times(2)).fire(1);
    verify(mockPr, times(2)).isEmpty();
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Three_Times_But_Primary_Is_Empty_Second(){
    // Arrange
    when(mockPr.fire(1)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    when(mockSc.fire(1)).thenReturn(true);
    when(mockSc.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPr, times(1)).fire(1);
    verify(mockPr, times(1)).isEmpty();
    assertEquals(true, result);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockSc, times(1)).isEmpty();
    verify(mockSc, times(1)).fire(1);
    assertEquals(true, result);

    when(mockPr.isEmpty()).thenReturn(true);

    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockPr, times(2)).isEmpty();
    verify(mockSc, times(2)).isEmpty();
    verify(mockSc, times(2)).fire(1);
    assertEquals(true, result);

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPr.getTorpedoCount()).thenReturn(10);
    when(mockPr.fire(10)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    when(mockSc.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockPr, times(1)).fire(10);
    verify(mockPr, times(1)).getTorpedoCount();
    verify(mockPr, times(1)).isEmpty();
    verify(mockSc, times(1)).isEmpty();
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Both_Success(){
    // Arrange
    when(mockPr.getTorpedoCount()).thenReturn(10);
    when(mockPr.fire(10)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(false);
    when(mockSc.isEmpty()).thenReturn(false);
    when(mockSc.getTorpedoCount()).thenReturn(10);
    when(mockSc.fire(10)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockPr, times(1)).fire(10);
    verify(mockPr, times(1)).getTorpedoCount();
    verify(mockPr, times(1)).isEmpty();
    verify(mockSc, times(1)).isEmpty();
    verify(mockSc, times(1)).fire(10);
    verify(mockSc, times(1)).getTorpedoCount();
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success_With_Only_Secondary(){
    // Arrange
    when(mockSc.getTorpedoCount()).thenReturn(10);
    when(mockSc.fire(10)).thenReturn(true);
    when(mockPr.isEmpty()).thenReturn(true);
    when(mockSc.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockSc, times(1)).fire(10);
    verify(mockSc, times(1)).getTorpedoCount();
    verify(mockPr, times(1)).isEmpty();
    verify(mockSc, times(1)).isEmpty();
    assertEquals(true, result);
  }
}
