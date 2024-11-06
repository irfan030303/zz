package model;

public class OrderDetailModel  {
	String id, jenis, qty, total,id_order,id_service;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getid_order() {
		return id_order ;
	}
	public  void setid_order(String id_order) {
		this.id_order = id_order;
	}
	public String getid_service() {
		return id_service ;
	}
	public  void setid_service(String id_service) {
		this.id_service = id_service;
	}
}