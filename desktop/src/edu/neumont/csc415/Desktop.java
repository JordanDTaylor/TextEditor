package edu.neumont.csc415;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;

public class Desktop {
    private static final int KEY_CODE_DELETE = 8;
    private static final int KEY_CODE_TAB = 9;
    private static final int KEY_CODE_NEW_LINE = 10;
    private static final int KEY_CODE_ESCAPE = 27;
    private static final int KEY_CODE_SPACE = 32;
    private static final int KEY_CODE_LEFT = 37;
    private static final int KEY_CODE_UP = 38;
    private static final int KEY_CODE_RIGHT = 39;
    private static final int KEY_CODE_DOWN = 40;
    private JFrame frame = new Desktop.DesktopFrame();
    private Vector<Integer> keyBuffer = new Vector();
    private Vector<MouseEvent> mouseBuffer = new Vector();
    private Font font = new Font(Font.MONOSPACED/*"Courier"*/, 0, 18);
    private Vector<IPaintable> paintables = new Vector();

    public Desktop(int width, int height) {
        this.frame.setSize(width + this.frame.getX(), height + this.frame.getY());
        this.frame.setFont(this.font);
        this.frame.setTitle("Desktop");
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
    }

    public void registerPaintable(IPaintable paintable) {
        if(this.paintables.contains(paintable)) {
            throw new RuntimeException("Paintable already registered");
        } else {
            this.paintables.add(paintable);
        }
    }

    public void unregisterPaintable(IPaintable paintable) {
        this.paintables.remove(paintable);
    }

    public void repaint() {
        this.frame.repaint();
    }

    public int getCharWidth(char ch) {
        FontMetrics metrics = this.frame.getGraphics().getFontMetrics();
        return metrics.stringWidth("" + ch);
    }

    public int getCharHeight() {
        FontMetrics metrics = this.frame.getGraphics().getFontMetrics();
        return metrics.getHeight();
    }

    public void close() {
        this.frame.dispose();
    }

    public boolean hasKeysQueued() {
        return !this.keyBuffer.isEmpty();
    }

    public int getKeyCode() {
        int keyCode = ((Integer)this.keyBuffer.remove(0)).intValue();
        return keyCode;
    }

    public boolean hasMouseEventsQueued() {
        return !this.mouseBuffer.isEmpty();
    }

    public MouseEvent getMouseEvent() {
        return (MouseEvent)this.mouseBuffer.remove(0);
    }

    private class DesktopFrame extends JFrame implements KeyListener, MouseListener {
        private int SHIFT_KEY = 16;
        private int CONTROL_KEY = 17;
        private int ALT_KEY = 18;
        private boolean shift = false;
        private boolean control = false;
        private boolean alt = false;

        public DesktopFrame() {
            this.addKeyListener(this);
            this.addMouseListener(this);
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            DesktopGraphics graphics = new DesktopGraphics(g2d);
            Iterator var5 = Desktop.this.paintables.iterator();

            while(var5.hasNext()) {
                IPaintable paintable = (IPaintable)var5.next();
                paintable.paint(graphics);
            }

        }

        public void keyPressed(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();
            if(code == this.SHIFT_KEY) {
                this.shift = true;
            } else if(code == this.CONTROL_KEY) {
                this.control = true;
            } else if(code == this.ALT_KEY) {
                this.alt = true;
            } else if(code != 8 && code != 9 && code != 10 && code != 32 && code != 27) {
                if(code == 37) {
                    Desktop.this.keyBuffer.add(Integer.valueOf(17));
                } else if(code == 38) {
                    Desktop.this.keyBuffer.add(Integer.valueOf(18));
                } else if(code == 39) {
                    Desktop.this.keyBuffer.add(Integer.valueOf(19));
                } else if(code == 40) {
                    Desktop.this.keyBuffer.add(Integer.valueOf(20));
                } else if(Desktop.this.font.canDisplay(code) && !this.control && !this.alt) {
                    if(this.shift) {
                        if(code >= 65 && code <= 90) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(code));
                        } else if(code == 96) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(126));
                        } else if(code == 49) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(33));
                        } else if(code == 50) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(64));
                        } else if(code == 51) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(35));
                        } else if(code == 52) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(36));
                        } else if(code == 53) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(37));
                        } else if(code == 54) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(94));
                        } else if(code == 55) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(38));
                        } else if(code == 56) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(42));
                        } else if(code == 57) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(40));
                        } else if(code == 48) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(41));
                        } else if(code == 45) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(95));
                        } else if(code == 61) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(43));
                        } else if(code == 91) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(123));
                        } else if(code == 93) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(125));
                        } else if(code == 59) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(58));
                        } else if(code == 39) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(34));
                        } else if(code == 44) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(60));
                        } else if(code == 46) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(62));
                        } else if(code == 47) {
                            Desktop.this.keyBuffer.add(Integer.valueOf(63));
                        }
                    } else if(code >= 65 && code <= 90) {
                        Desktop.this.keyBuffer.add(Integer.valueOf(code + 32));
                    } else {
                        Desktop.this.keyBuffer.add(Integer.valueOf(code));
                    }
                }
            } else {
                Desktop.this.keyBuffer.add(Integer.valueOf(code));
            }

        }

        public void keyReleased(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();
            if(code == this.SHIFT_KEY) {
                this.shift = false;
            } else if(code == this.CONTROL_KEY) {
                this.control = false;
            } else if(code == this.ALT_KEY) {
                this.alt = false;
            }

        }

        public void keyTyped(KeyEvent keyEvent) {
        }

        public void mouseClicked(MouseEvent mouseEvent) {
        }

        public void mouseEntered(MouseEvent mouseEvent) {
        }

        public void mouseExited(MouseEvent mouseEvent) {
        }

        public void mousePressed(MouseEvent mouseEvent) {
            Desktop.this.mouseBuffer.add(mouseEvent);
        }

        public void mouseReleased(MouseEvent mouseEvent) {
            Desktop.this.mouseBuffer.add(mouseEvent);
        }
    }
}
