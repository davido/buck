/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-02-20")
public class LogLineBatchRequest implements org.apache.thrift.TBase<LogLineBatchRequest, LogLineBatchRequest._Fields>, java.io.Serializable, Cloneable, Comparable<LogLineBatchRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LogLineBatchRequest");

  private static final org.apache.thrift.protocol.TField SLAVE_STREAM_FIELD_DESC = new org.apache.thrift.protocol.TField("slaveStream", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField BATCH_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("batchNumber", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LogLineBatchRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LogLineBatchRequestTupleSchemeFactory());
  }

  public SlaveStream slaveStream; // optional
  public int batchNumber; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SLAVE_STREAM((short)1, "slaveStream"),
    BATCH_NUMBER((short)2, "batchNumber");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SLAVE_STREAM
          return SLAVE_STREAM;
        case 2: // BATCH_NUMBER
          return BATCH_NUMBER;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __BATCHNUMBER_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SLAVE_STREAM,_Fields.BATCH_NUMBER};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SLAVE_STREAM, new org.apache.thrift.meta_data.FieldMetaData("slaveStream", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, SlaveStream.class)));
    tmpMap.put(_Fields.BATCH_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("batchNumber", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LogLineBatchRequest.class, metaDataMap);
  }

  public LogLineBatchRequest() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LogLineBatchRequest(LogLineBatchRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetSlaveStream()) {
      this.slaveStream = new SlaveStream(other.slaveStream);
    }
    this.batchNumber = other.batchNumber;
  }

  public LogLineBatchRequest deepCopy() {
    return new LogLineBatchRequest(this);
  }

  @Override
  public void clear() {
    this.slaveStream = null;
    setBatchNumberIsSet(false);
    this.batchNumber = 0;
  }

  public SlaveStream getSlaveStream() {
    return this.slaveStream;
  }

  public LogLineBatchRequest setSlaveStream(SlaveStream slaveStream) {
    this.slaveStream = slaveStream;
    return this;
  }

  public void unsetSlaveStream() {
    this.slaveStream = null;
  }

  /** Returns true if field slaveStream is set (has been assigned a value) and false otherwise */
  public boolean isSetSlaveStream() {
    return this.slaveStream != null;
  }

  public void setSlaveStreamIsSet(boolean value) {
    if (!value) {
      this.slaveStream = null;
    }
  }

  public int getBatchNumber() {
    return this.batchNumber;
  }

  public LogLineBatchRequest setBatchNumber(int batchNumber) {
    this.batchNumber = batchNumber;
    setBatchNumberIsSet(true);
    return this;
  }

  public void unsetBatchNumber() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BATCHNUMBER_ISSET_ID);
  }

  /** Returns true if field batchNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetBatchNumber() {
    return EncodingUtils.testBit(__isset_bitfield, __BATCHNUMBER_ISSET_ID);
  }

  public void setBatchNumberIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BATCHNUMBER_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SLAVE_STREAM:
      if (value == null) {
        unsetSlaveStream();
      } else {
        setSlaveStream((SlaveStream)value);
      }
      break;

    case BATCH_NUMBER:
      if (value == null) {
        unsetBatchNumber();
      } else {
        setBatchNumber((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SLAVE_STREAM:
      return getSlaveStream();

    case BATCH_NUMBER:
      return getBatchNumber();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SLAVE_STREAM:
      return isSetSlaveStream();
    case BATCH_NUMBER:
      return isSetBatchNumber();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LogLineBatchRequest)
      return this.equals((LogLineBatchRequest)that);
    return false;
  }

  public boolean equals(LogLineBatchRequest that) {
    if (that == null)
      return false;

    boolean this_present_slaveStream = true && this.isSetSlaveStream();
    boolean that_present_slaveStream = true && that.isSetSlaveStream();
    if (this_present_slaveStream || that_present_slaveStream) {
      if (!(this_present_slaveStream && that_present_slaveStream))
        return false;
      if (!this.slaveStream.equals(that.slaveStream))
        return false;
    }

    boolean this_present_batchNumber = true && this.isSetBatchNumber();
    boolean that_present_batchNumber = true && that.isSetBatchNumber();
    if (this_present_batchNumber || that_present_batchNumber) {
      if (!(this_present_batchNumber && that_present_batchNumber))
        return false;
      if (this.batchNumber != that.batchNumber)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_slaveStream = true && (isSetSlaveStream());
    list.add(present_slaveStream);
    if (present_slaveStream)
      list.add(slaveStream);

    boolean present_batchNumber = true && (isSetBatchNumber());
    list.add(present_batchNumber);
    if (present_batchNumber)
      list.add(batchNumber);

    return list.hashCode();
  }

  @Override
  public int compareTo(LogLineBatchRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSlaveStream()).compareTo(other.isSetSlaveStream());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSlaveStream()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.slaveStream, other.slaveStream);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBatchNumber()).compareTo(other.isSetBatchNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBatchNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.batchNumber, other.batchNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("LogLineBatchRequest(");
    boolean first = true;

    if (isSetSlaveStream()) {
      sb.append("slaveStream:");
      if (this.slaveStream == null) {
        sb.append("null");
      } else {
        sb.append(this.slaveStream);
      }
      first = false;
    }
    if (isSetBatchNumber()) {
      if (!first) sb.append(", ");
      sb.append("batchNumber:");
      sb.append(this.batchNumber);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (slaveStream != null) {
      slaveStream.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class LogLineBatchRequestStandardSchemeFactory implements SchemeFactory {
    public LogLineBatchRequestStandardScheme getScheme() {
      return new LogLineBatchRequestStandardScheme();
    }
  }

  private static class LogLineBatchRequestStandardScheme extends StandardScheme<LogLineBatchRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LogLineBatchRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SLAVE_STREAM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.slaveStream = new SlaveStream();
              struct.slaveStream.read(iprot);
              struct.setSlaveStreamIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BATCH_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.batchNumber = iprot.readI32();
              struct.setBatchNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, LogLineBatchRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.slaveStream != null) {
        if (struct.isSetSlaveStream()) {
          oprot.writeFieldBegin(SLAVE_STREAM_FIELD_DESC);
          struct.slaveStream.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetBatchNumber()) {
        oprot.writeFieldBegin(BATCH_NUMBER_FIELD_DESC);
        oprot.writeI32(struct.batchNumber);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LogLineBatchRequestTupleSchemeFactory implements SchemeFactory {
    public LogLineBatchRequestTupleScheme getScheme() {
      return new LogLineBatchRequestTupleScheme();
    }
  }

  private static class LogLineBatchRequestTupleScheme extends TupleScheme<LogLineBatchRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LogLineBatchRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSlaveStream()) {
        optionals.set(0);
      }
      if (struct.isSetBatchNumber()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSlaveStream()) {
        struct.slaveStream.write(oprot);
      }
      if (struct.isSetBatchNumber()) {
        oprot.writeI32(struct.batchNumber);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LogLineBatchRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.slaveStream = new SlaveStream();
        struct.slaveStream.read(iprot);
        struct.setSlaveStreamIsSet(true);
      }
      if (incoming.get(1)) {
        struct.batchNumber = iprot.readI32();
        struct.setBatchNumberIsSet(true);
      }
    }
  }

}

