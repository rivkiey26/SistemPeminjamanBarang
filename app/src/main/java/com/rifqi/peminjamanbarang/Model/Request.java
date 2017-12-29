package com.rifqi.peminjamanbarang.Model;

import java.util.List;

/**
 * Created by USER on 28/12/2017.
 */

public class Request {

    private String name;

    private String total;
    private String keperluan;
    private String status;
    private List<Pinjam> alat;

    public List<Pinjam> getAlat() {
        return alat;
    }

    public void setAlat(List<Pinjam> alat) {
        this.alat = alat;
    }

    public Request(String name, String total, String keperluan, List<Pinjam> alat) {

        this.name = name;

        this.total = total;

        this.alat = alat;
        this.keperluan = keperluan;

    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public Request() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
