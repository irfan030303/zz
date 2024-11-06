package model;

public class Order {

	    private String Id;
	    private String id_costumer;
	    private String costumer;
	    private String id_service;
	    private String id_user;
	    private String total;
	    private String tanggal;
	    private String tanggal_selesai;
	    private String status;
	    private String Pembayaran;
	    private String status_pembayaran;

	    public String getId() {
	        return Id;
	    }

	    public void setId(String id) {
	        Id = id;
	    }

	    public String getCostumer() {
	        return costumer;
	    }

	    public void setCostumer(String costumer) {
	        this.costumer = costumer;
	    }

	    public String getId_costumer() {
	        return id_costumer;
	    }

	    public void setId_costumer(String id_costumer) {
	        this.id_costumer = id_costumer;
	    }

	    public String getId_service() {
	        return id_service;
	    }

	    public void setId_service(String id_service) {
	        this.id_service = id_service;
	    }

	    public String getId_user() {
	        return id_user;
	    }

	    public void setId_user(String id_user) {
	        this.id_user = id_user;
	    }

	    public String getTotal() {
	        return total;
	    }

	    public void setTotal(String total) {
	        this.total = total;
	    }

	    public String getTanggal() {
	        return tanggal;
	    }

	    public void setTanggal(String tanggal) {
	        this.tanggal = tanggal;
	    }

	    public String getTanggal_selesai() {
	        return tanggal_selesai;
	    }

	    public void setTanggal_selesai(String tanggal_selesai) {
	        this.tanggal_selesai = tanggal_selesai;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getPembayaran() {
	        return Pembayaran;
	    }

	    public void setPembayaran(String Pembayaran) {
	        this.Pembayaran = Pembayaran;
	    }

	    public String getStatus_pembayaran() {
	        return status_pembayaran;
	    }

	    public void setStatus_pembayaran(String status_pembayaran) {
	        this.status_pembayaran = status_pembayaran;
	    }
	}
