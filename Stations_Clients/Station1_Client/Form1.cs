using Station1_Client.ServiceReference1;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Station1_Client
{
    public partial class Form1 : Form
    {
        private Service service;

        public Form1()
        {
            InitializeComponent();
            service = new ServiceClient();
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                bool result = service.stationOneRegister(textBox_VIN.Text.ToString(), textBox_Model.Text.ToString(),
                    textBox_Brand.Text.ToString(), int.Parse(textBox_Car_weight.Text.ToString()));
                if(result == true)
                {
                    textBox_results.Text = "Car inserted successfully!";
                }
                else
                {
                    textBox_results.Text = "Error in execution! Car was not inserted!";
                }
                
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void tabPage1_Click(object sender, EventArgs e)
        {

        }

        private void button_Register_Part_Click(object sender, EventArgs e)
        {
            try
            {
                int part_id = service.stationTwoRegister(textBox_PartName.Text.ToString(), textBox_PartType.Text.ToString(),
                double.Parse(textBox_Weight_station2.Text.ToString()), textBox_VIN_station2.Text.ToString(),
                int.Parse(textBox_Pallet_ID.Text.ToString()));
                if (part_id == -1)
                {
                    textBox_Result_Station_2.Text = "Part was not added successfully!";
                    textBox_result_station2_part_id.Text = part_id.ToString();
                }
                else
                {
                    textBox_Result_Station_2.Text = "Part was added successfully!";
                    textBox_result_station2_part_id.Text = part_id.ToString();
                }
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void button_copyProductID_Click(object sender, EventArgs e)
        {
            try
            {
                if (textBox_resultingProductID.Text.ToString() != "-1")
                {
                    textBox_ProductID_station3.Clear();
                    textBox_ProductID_station3.Text = textBox_resultingProductID.Text.ToString();
                }
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void button_RegisterOrder_station3_Click(object sender, EventArgs e)
        {
            try
            {
                string test = comboBox_typeOfShipment.Text.ToString();
                int result = service.productRegistration(textBox_ProductName.Text.ToString(), comboBox_typeOfShipment.Text.ToString(),
                    textBox_destination_station3.Text.ToString());
                if(result == -1)
                {
                    textBox_result1_station3.Text = "Operation unsuccesful, please review your text";
                    textBox_resultingProductID.Text = result.ToString();
                }
                else
                {
                    textBox_result1_station3.Text = "Operation succesful! You may add parts to shipment";
                    textBox_resultingProductID.Text = result.ToString();
                }

            }catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void button_AddProduct_Click(object sender, EventArgs e)
        {
            try
            {
                 
                bool result = service.insertProductPart(int.Parse(textBox_ProductID_station3.Text.ToString())
                    , int.Parse(textBox_PartID_station3.Text.ToString()));

                if (result)
                {
                    textBox_result2_station3.Text = "Product added successfully!";
                }
                else
                {
                    textBox_result2_station3.Text = "Product was not added!";
                }
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void label22_Click(object sender, EventArgs e)
        {

        }

        private void label23_Click(object sender, EventArgs e)
        {

        }

        private void button_search_Click(object sender, EventArgs e)
        {
            try
            {
                listBox_search.Items.Clear();
                searchCarResponse results =  service.searchCar(new searchCarRequest(textBox_VIN_search.Text.ToString()));
                if (!results.searchCarReturn[0].Equals(""))
                {
                    for (int i = 0; i < results.searchCarReturn.Length; i++)
                    {
                        listBox_search.Items.Add(results.searchCarReturn[i]);
                    }
                }
                else if (results.searchCarReturn[0].Equals(""))
                {
                    listBox_search.Items.Add("Car does not exist in DB");
                }
            }catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }
    }
}
