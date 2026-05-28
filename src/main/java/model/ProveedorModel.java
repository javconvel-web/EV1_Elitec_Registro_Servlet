package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {

	public int  registraProveedor(Proveedor obj) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			//1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();
			
			//2 Crear el SQL de insercion
			String sql = "INSERT INTO proveedor (razonsocial, ruc, direccion, correo, gerente, fechaCreacion) VALUES (?,?,?,?,?,?)";
			
			//3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);
			ps.setString(1, obj.getRazonSocial());
			ps.setString(2, obj.getRuc());
			ps.setString(3, obj.getDireccion());
			ps.setString(4, obj.getCorreo());
			ps.setString(5, obj.getGerente());
			ps.setDate(6, java.sql.Date.valueOf(obj.getFechaCreacion()));
			
			System.out.println("SQL: " + ps);
			
			//4 Ejecutar el SQL	
			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
}