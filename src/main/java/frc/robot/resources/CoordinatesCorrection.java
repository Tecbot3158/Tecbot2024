package frc.robot.resources;


public class CoordinatesCorrection {

    static double AngleBRS2 = 69/40;
    static double AngleBLS2 = -69/40;
    static double AngleRRS2 = -69/40;
    static double AngleRLS2 = 69/40;

        enum Site {
            BlueRightSite,
            BlueMidSite,
            BlueLeftSite,
            RedRightSite,
            RedMidSite,
            RedLeftSite
        }

        /*
         * new x = x⋅cos(theta) + y⋅sin(theta)
         * new y = -x⋅sin(theta) + y⋅cos(theta)
         */
    
        public static double[] transformarCoordenadas( double x, double y, Site site) {
            double[] coordenadas = new double[2];
            switch (site) {
                case BlueLeftSite:
                    coordenadas[0] = x*Math.cos(AngleBRS2) + y*Math.sin(AngleBRS2);
                    coordenadas[1] = -x*Math.sin(AngleBRS2) + y*Math.cos(AngleBRS2);
                    break;
                case BlueMidSite:
                    coordenadas[0] = x;
                    coordenadas[1] = y;
                    break;
                case BlueRightSite:
                    coordenadas[0] = x*Math.cos(AngleBLS2) + y*Math.sin(AngleBLS2);
                    coordenadas[1] = -x*Math.sin(AngleBLS2) + y*Math.cos(AngleBLS2);
                    break;
                case RedRightSite:
                    coordenadas[0] = -(x*Math.cos(AngleRRS2) + y*Math.sin(AngleRRS2));
                    coordenadas[1] = -(-x*Math.sin(AngleRRS2) + y*Math.cos(AngleRRS2));
                    break;
                case RedMidSite:
                    coordenadas[0] = -x;
                    coordenadas[1] = -y;
                    break;
                case RedLeftSite:
                    coordenadas[0] = -(x*Math.cos(AngleRLS2) + y*Math.sin(AngleRLS2));
                    coordenadas[1] = -(-x*Math.sin(AngleRLS2) + y*Math.cos(AngleRLS2));
                    break;
                default:
                    break;
            }
    
            return coordenadas;
        }

        public static void main(String[] args) {
        
            // Transformación de coordenadas para cada eje
            for (Site site : Site.values()) {
                double[] nuevaCoordenada = transformarCoordenadas(1, 1.3, site);
                System.out.println( "Site " + site + "  x: " + nuevaCoordenada[0] + "  y: " + nuevaCoordenada[1] );

            }
        }
    }